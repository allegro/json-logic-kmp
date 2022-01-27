package expressions

import JsonLogicEngine
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class VarTest : FunSpec({
    context("JsonLogic evaluation with only Var operation") {
        withData(
            // given
            listOf(
                /* Unsupported test cases
                    TestInput(
                        expression = mapOf(
                            "and" to listOf(
                                mapOf(
                                    "<" to listOf(mapOf("var" to "temp"), 110)
                                ),
                                mapOf("==" to listOf(mapOf("var" to "pie.filling"), "apple"))
                            )
                        ),
                        data = mapOf(
                            "temp" to 100,
                            "pie" to mapOf("filling" to "apple")
                        ),
                        result = true
                    ),
                    TestInput(
                        expression = mapOf(
                            "var" to listOf(
                                mapOf(
                                    "?" to listOf(
                                        mapOf("<" to listOf(mapOf("var" to "temp"), 110)), "pie.filling", "pie.eta"
                                    )
                                )
                            )
                        ),
                        data = mapOf("temp" to 100, "pie" to mapOf("filling" to "apple", "eta" to "60s")), result = "apple"
                    ),
                    TestInput(
                        expression = mapOf(
                            "in" to listOf(
                                mapOf("var" to "filling"),
                                listOf("apple", "cherry")
                            )
                        ),
                        data = mapOf("filling" to "apple"),
                        result = true
                    ),
                */
                TestInput(expression = mapOf("var" to listOf("a")), data = mapOf("a" to 1), result = 1),
                TestInput(expression = mapOf("var" to listOf("b")), data = mapOf("a" to 1), result = null),
                TestInput(expression = mapOf("var" to listOf("b")), data = null, result = null),
                TestInput(expression = mapOf("var" to "a"), data = mapOf("a" to 1), result = 1),
                TestInput(expression = mapOf("var" to "b"), data = mapOf("a" to 1), result = null),
                TestInput(expression = mapOf("var" to "a"), data = null, result = null),
                TestInput(expression = mapOf("var" to listOf("a", 1)), data = null, result = 1),
                TestInput(expression = mapOf("var" to listOf("b", 2)), data = mapOf("a" to 1), result = 2),
                TestInput(expression = mapOf("var" to "a.b"), data = mapOf("a" to mapOf("b" to "c")), result = "c"),
                TestInput(expression = mapOf("var" to "a.q"), data = mapOf("a" to mapOf("b" to "c")), result = null),
                TestInput(
                    expression = mapOf("var" to listOf("a.q", 9)),
                    data = mapOf("a" to mapOf("b" to "c")),
                    result = 9
                ),
                TestInput(expression = mapOf("var" to 1), data = listOf("apple", "banana"), result = "banana"),
                TestInput(expression = mapOf("var" to "1"), data = listOf("apple", "banana"), result = "banana"),
                TestInput(
                    expression = mapOf("var" to "1.1"),
                    data = listOf("apple", listOf("banana", "beer")),
                    result = "beer"
                ),
                TestInput(expression = mapOf("var" to "a.b.c"), data = null, result = null),
                TestInput(expression = mapOf("var" to "a.b.c"), data = mapOf("a" to null), result = null),
                TestInput(expression = mapOf("var" to "a.b.c"), data = mapOf("a" to mapOf("b" to null)), result = null),
                TestInput(expression = mapOf("var" to ""), data = 1, result = 1),
                TestInput(expression = mapOf("var" to null), data = 1, result = 1),
                TestInput(expression = mapOf("var" to emptyList<Any>()), data = 1, result = 1),
            )
        ) { (expression, data, result) ->
            // when
            val evaluationResult = JsonLogicEngine.instance.evaluate(expression, data)

            // then
            evaluationResult shouldBe result
        }
    }
})
