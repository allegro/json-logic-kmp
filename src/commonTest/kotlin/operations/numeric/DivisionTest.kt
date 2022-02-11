package operations.numeric

import JsonLogicEngine
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class DivisionTest : FunSpec({
    context("JsonLogic evaluation with only Division operation") {
        withData(
            nameFn = { "Should apply ${it.data} on ${it.expression} result in ${it.result}" },
            // given
            ts = listOf(
                TestInput(
                    expression = mapOf("/" to listOf(4, 2)),
                    result = 2
                ),
                TestInput(
                    expression = mapOf("/" to listOf(2, 4)),
                    result = 0.5
                ),
                TestInput(
                    expression = mapOf("/" to listOf("1", 1)),
                    result = 1
                ),
                TestInput(
                    expression = mapOf("/" to listOf("1", 0)),
                    result = null
                ),
                TestInput(
                    expression = mapOf("/" to listOf(0, 1)),
                    result = 0
                ),
                TestInput(
                    expression = mapOf("/" to listOf("1", "0")),
                    result = null
                ),
                TestInput(
                    expression = mapOf("/" to listOf("2.5", "2")),
                    result = 1.25
                ),
                TestInput(
                    expression = mapOf("/" to listOf("2.5", "2", "3", 5)),
                    result = 1.25
                ),
                TestInput(
                    expression = mapOf("/" to listOf("2.5", listOf("2", "3", 5))),
                    result = null
                ),
                TestInput(
                    expression = mapOf("/" to listOf(listOf("2.5"), 2)),
                    result = null
                ),
                TestInput(
                    expression = mapOf("/" to listOf(null, 5)),
                    result = 0
                ),
                TestInput(
                    expression = mapOf("/" to listOf(2, null)),
                    result = null
                ),
                TestInput(
                    expression = mapOf("/" to listOf(null, null)),
                    result = null
                ),
                TestInput(
                    expression = mapOf("/" to listOf(null)),
                    result = null
                ),
                TestInput(
                    expression = mapOf("/" to listOf("banana")),
                    result = null
                ),
                TestInput(
                    expression = mapOf("/" to listOf(true, false)),
                    result = null
                ),
                TestInput(
                    expression = mapOf("/" to listOf(true)),
                    result = null
                ),
                TestInput(
                    expression = mapOf("/" to listOf(false)),
                    result = null
                ),
                TestInput(
                    expression = mapOf("/" to listOf(true, null)),
                    result = null
                ),
                TestInput(
                    expression = mapOf("/" to listOf(false, null)),
                    result = null
                ),
                TestInput(
                    expression = mapOf("/" to listOf(false, true)),
                    result = 0
                ),
                TestInput(
                    expression = mapOf("/" to listOf(0, true)),
                    result = 0
                ),
                TestInput(
                    expression = mapOf("/" to listOf(1, true)),
                    result = 1
                ),
                TestInput(
                    expression = mapOf("/" to listOf("a", 2)),
                    result = null
                ),
                TestInput(
                    expression = mapOf("/" to listOf(listOf(2, "a"), 2)),
                    result = null
                ),
                TestInput(
                    expression = mapOf("/" to listOf(listOf("a", 2), 2)),
                    result = null
                ),
                TestInput(
                    expression = mapOf("/" to listOf(listOf(2, 2), 2)),
                    result = null
                ),
                TestInput(
                    expression = mapOf("/" to listOf(listOf(2, "a"), listOf("a", 2))),
                    result = null
                ),
                TestInput(
                    expression = mapOf("/" to listOf(listOf("5"), listOf("5"), listOf("5"))),
                    result = 1
                ),
                TestInput(
                    expression = mapOf("/" to listOf(listOf("5", listOf("5")), listOf("5"), listOf("5"))),
                    result = null
                ),
                TestInput(
                    expression = mapOf("/" to listOf(listOf("5"), 5)),
                    result = 1
                ),
                TestInput(
                    expression = mapOf("/" to listOf(listOf(listOf("5")), 5)),
                    result = 1
                ),
                TestInput(
                    expression = mapOf("/" to listOf(listOf(listOf("5")), listOf(5))),
                    result = 1
                ),
                TestInput(
                    expression = mapOf("/" to listOf(listOf(listOf("5"), listOf(6)))),
                    result = null
                ),
                TestInput(
                    expression = mapOf("/" to listOf(emptyList<String>(), 2)),
                    result = 0
                )
            )
        ) { (expression, data, result) ->
            // when
            val evaluationResult = JsonLogicEngine.instance.evaluate(expression, data)

            // then
            evaluationResult shouldBe result
        }
    }
})
