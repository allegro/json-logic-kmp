package operations.numeric

import JsonLogicEngine
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class AdditionTest : FunSpec({
    context("JsonLogic evaluation with only Addition operation") {
        withData(
            nameFn = { "Should apply ${it.data} on ${it.expression} result in ${it.result}" },
            // given
            ts = listOf(
                TestInput(
                    expression = mapOf("+" to listOf(1, 2)),
                    result = 3
                ),
                TestInput(
                    expression = mapOf("+" to listOf(2, 2, 2)),
                    result = 6
                ),
                TestInput(
                    expression = mapOf("+" to listOf(1)),
                    result = 1
                ),
                TestInput(
                    expression = mapOf("+" to listOf("1", 1)),
                    result = 2
                ),
                TestInput(
                    expression = mapOf("+" to listOf("1", 1.5)),
                    result = 2.5
                ),
                TestInput(
                    expression = mapOf("+" to listOf("1", 1.5, "banana")),
                    result = null
                ),
                TestInput(
                    expression = mapOf("+" to listOf("1", 1, listOf("banana"))),
                    result = null
                ),
                TestInput(
                    expression = mapOf("+" to listOf(12.6543534, 1.1)),
                    result = 13.7543534
                ),
                TestInput(
                    expression = mapOf("+" to listOf(9, .9, .09, .009, .0009, .00009)),
                    result = 9.99999
                ),
                TestInput(
                    expression = mapOf("+" to listOf("5")),
                    result = 5
                ),
                TestInput(
                    expression = mapOf("+" to listOf("a", 2)),
                    result = null
                ),
                TestInput(
                    expression = mapOf("+" to listOf(listOf(2, "a"), 2)),
                    result = 4
                ),
                TestInput(
                    expression = mapOf("+" to listOf(listOf("a", 2), 2)),
                    result = null
                ),
                TestInput(
                    expression = mapOf("+" to listOf(listOf(2, 2), 2)),
                    result = 4
                ),
                TestInput(
                    expression = mapOf("+" to listOf(listOf(2, "a"), listOf("a", 2))),
                    result = null
                ),
                TestInput(
                    expression = mapOf("+" to listOf(listOf("5"), listOf("5"), listOf("5"))),
                    result = 15
                ),
                TestInput(
                    expression = mapOf("+" to listOf(listOf("5", listOf("5")), listOf("5"), listOf("5"))),
                    result = 15
                ),
                TestInput(
                    expression = mapOf("+" to listOf(listOf("5"), 6)),
                    result = 11
                ),
                TestInput(
                    expression = mapOf("+" to listOf(listOf(listOf("5")), 6)),
                    result = 11
                ),
                TestInput(
                    expression = mapOf("+" to listOf(listOf(listOf("5")), listOf(6))),
                    result = 11
                ),
                TestInput(
                    expression = mapOf("+" to listOf(listOf(listOf("5"), listOf(6)))),
                    result = 5
                ),
                TestInput(
                    expression = mapOf("+" to listOf(listOf("5"), listOf("6"))),
                    result = 11
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
