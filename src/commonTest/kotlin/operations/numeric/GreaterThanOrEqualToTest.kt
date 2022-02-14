package operations.numeric

import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class GreaterThanOrEqualToTest : FunSpec({
    context("JsonLogic evaluation with only GreaterThanOrEqualTo operation") {
        withData(
            nameFn = { "Should apply ${it.data} on ${it.expression} result in ${it.result}"},
            // given
            ts = listOf(
                TestInput(
                    expression = mapOf(">=" to listOf(2, 1)),
                    result = true
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(1, 1)),
                    result = true
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(1, 2)),
                    result = false
                ),
                TestInput(
                    expression = mapOf(">=" to listOf("2", 1)),
                    result = true
                ),
                TestInput(
                    expression = mapOf(">=" to listOf("2", "banana")),
                    result = false
                ),
                TestInput(
                    expression = mapOf(">=" to listOf("apple", "banana")),
                    result = false
                ),
                TestInput(
                    expression = mapOf(">=" to listOf("grapes", "banana")),
                    result = true
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(1, "banana")),
                    result = false
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(1, listOf("banana"))),
                    result = false
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(1, 2, 3)),
                    result = false
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(1, 2, 3, 4)),
                    result = false
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(1, 1, 3)),
                    result = true
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(1, 1, 3, 0)),
                    result = true
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(1, 4, 3)),
                    result = false
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(4, 1, 3)),
                    result = true
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(4, 3, 1)),
                    result = true
                ),
                TestInput(
                    expression = mapOf(">=" to listOf("4", 3, "1")),
                    result = true
                ),
                TestInput(
                    expression = mapOf(">=" to listOf("banana", 3, "1")),
                    result = false
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(4, "3", 1)),
                    result = true
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(0, mapOf("var" to "temp"), 100)),
                    data = mapOf("temp" to 37),
                    result = false
                ),
                TestInput(
                    expression = mapOf(">=" to listOf("grapes", true)),
                    result = false
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(1, false)),
                    result = true
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(false, 3, 3)),
                    result = false
                ),
                TestInput(
                    expression = mapOf(">=" to listOf("banana", 3, 3)),
                    result = false
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(listOf(1, 2, 3), listOf(3))),
                    result = false
                ),
                TestInput(
                    expression = mapOf(">=" to listOf("true", true)),
                    result = false
                ),
                TestInput(
                    expression = mapOf(">=" to listOf("true", false)),
                    result = false
                ),
                TestInput(
                    expression = mapOf(">=" to listOf("true", "false")),
                    result = true
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(true, false)),
                    result = true
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(true, "false")),
                    result = false
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(false, "false")),
                    result = false
                ),
                TestInput(
                    expression = mapOf(">=" to listOf("banana", "2")),
                    result = true
                ),
                TestInput(
                    expression = mapOf(">=" to listOf("banana", 2)),
                    result = false
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
