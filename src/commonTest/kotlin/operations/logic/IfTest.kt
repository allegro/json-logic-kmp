package operations.logic

import JsonLogicEngine
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
/*
"If/then/elseif/then cases",
[{"if":[false, "apple", false, "banana", false, "carrot"]}, null, null],
[{"if":[false, "apple", false, "banana", false, "carrot", "date"]}, null, "date"],
[{"if":[false, "apple", false, "banana", true, "carrot", "date"]}, null, "carrot"],
[{"if":[false, "apple", true, "banana", false, "carrot", "date"]}, null, "banana"],
[{"if":[false, "apple", true, "banana", true, "carrot", "date"]}, null, "banana"],
[{"if":[true, "apple", false, "banana", false, "carrot", "date"]}, null, "apple"],
[{"if":[true, "apple", false, "banana", true, "carrot", "date"]}, null, "apple"],
[{"if":[true, "apple", true, "banana", false, "carrot", "date"]}, null, "apple"],
[{"if":[true, "apple", true, "banana", true, "carrot", "date"]}, null, "apple"],
[{"if": [{"var": "x"}, [{"var": "y"}], 99]}, {"x": true, "y": 42}, [42]],
*/
class IfTest : FunSpec({
    context("JsonLogic evaluation with If operation") {
        withData(
            nameFn = { "Should apply ${it.data} on ${it.expression} result in ${it.result}" },
            // given
            ts = listOf(
                TestInput(expression = mapOf("if" to listOf(true, "apple", true, "banana", "carrot")), result = "apple"),
                TestInput(expression = mapOf("if" to listOf(true, "apple", false, "banana", "carrot")), result = "apple"),
                TestInput(expression = mapOf("if" to listOf(false, "apple", true, "banana", "carrot")), result = "banana"),
                TestInput(expression = mapOf("if" to listOf(false, "apple", false, "banana", "carrot")), result = "carrot"),

                TestInput(expression = mapOf("if" to listOf(true, "apple", true, "banana")), result = "apple"),
                TestInput(expression = mapOf("if" to listOf(true, "apple", false, "banana")), result = "apple"),
                TestInput(expression = mapOf("if" to listOf(false, "apple", true, "banana")), result = "banana"),
                TestInput(expression = mapOf("if" to listOf(false, "apple", false, "banana")), result = null),


                TestInput(expression = mapOf("if" to listOf(mapOf("missing" to "a"), "missed it", "found it")), data = mapOf("a" to "apple"), result = "found it"),
                TestInput(expression = mapOf("if" to listOf(mapOf("missing" to "a"), "missed it", "found it")), data = mapOf("b" to "banana"), result = "missed it"),

                TestInput(expression = mapOf("if" to listOf(true, mapOf("cat" to listOf("ap", "ple")), mapOf("cat" to listOf("ba", "na", "na")))), result = "apple"),
                TestInput(expression = mapOf("if" to listOf(false, mapOf("cat" to listOf("ap", "ple")), mapOf("cat" to listOf("ba", "na", "na")))), result = "banana"),

                TestInput(expression = mapOf("if" to listOf(mapOf(">" to listOf(2,1)), "apple", "banana")), result = "apple"),
                TestInput(expression = mapOf("if" to listOf(mapOf(">" to listOf(1,2)), "apple", "banana")), result = "banana"),

                TestInput(expression = mapOf("if" to listOf<Any>()), result = null),
                TestInput(expression = mapOf("if" to listOf(true)), result = true),
                TestInput(expression = mapOf("if" to listOf(false)), result = false),
                TestInput(expression = mapOf("if" to listOf("apple")), result = "apple"),
                TestInput(expression = mapOf("if" to listOf(true, "apple")), result = "apple"),
                TestInput(expression = mapOf("if" to listOf(false, "apple")), result = null),
                TestInput(expression = mapOf("if" to listOf(emptyList<Any>(), "apple", "banana")), result = "banana"),
                TestInput(expression = mapOf("if" to listOf(listOf(1), "apple", "banana")), result = "apple"),
                TestInput(expression = mapOf("if" to listOf(listOf(1, 2, 3, 4), "apple", "banana")), result = "apple"),
                TestInput(expression = mapOf("if" to listOf("", "apple", "banana")), result = "banana"),
                TestInput(expression = mapOf("if" to listOf("zucchini", "apple", "banana")), result = "apple"),
                TestInput(expression = mapOf("if" to listOf("0", "apple", "banana")), result = "apple"),
                TestInput(expression = mapOf("if" to listOf(mapOf("+" to "0"), "apple", "banana")), result = "banana"),
                TestInput(expression = mapOf("if" to listOf(mapOf("+" to "1"), "apple", "banana")), result = "apple"),
                TestInput(expression = mapOf("if" to listOf(0, "apple", "banana")), result = "banana"),
                TestInput(expression = mapOf("if" to listOf(1, "apple", "banana")), result = "apple"),
                TestInput(expression = mapOf("if" to listOf(3.1416, "apple", "banana")), result = "apple"),
                TestInput(expression = mapOf("if" to listOf(-1, "apple", "banana")), result = "apple"),

                TestInput(expression = mapOf("if" to listOf(true, "yes", "no")), result = "yes"),
                TestInput(expression = mapOf("if" to listOf(false, "yes", "no")), result = "no"),
                TestInput(
                    expression = mapOf(
                        "if" to listOf(
                            mapOf("<" to listOf(mapOf("var" to "temp"), 0)), "freezing",
                            mapOf("<" to listOf(mapOf("var" to "temp"), 100)), "liquid",
                            "gas"
                        )
                    ),
                    data = mapOf("temp" to 55),
                    result = "liquid"
                ),
            )
        ) { (expression, data, result) ->
            // when
            val evaluationResult = JsonLogicEngine.instance.evaluate(expression, data)

            // then
            evaluationResult shouldBe result
        }
    }
})
