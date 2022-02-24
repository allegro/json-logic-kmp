package operations.logic

import JsonLogicEngine
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe


class IfTest : FunSpec({
    context("JsonLogic evaluation with If operation") {
        withData(
            nameFn = { "Should apply ${it.data} on ${it.expression} result in ${it.result}" },
            // given
            ts = listOf(
                TestInput(expression = mapOf("if" to listOf<Any>()), result = null),
                TestInput(expression = mapOf("if" to listOf(true)), result = true),
                TestInput(expression = mapOf("if" to listOf(false)), result = false),
                TestInput(expression = mapOf("if" to listOf("apple")), result = "apple"),

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
