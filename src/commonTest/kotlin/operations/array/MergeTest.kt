package operations.array

import JsonLogicEngine
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class MergeTest : FunSpec({
    context("JsonLogic evaluation with Merge operation") {
        withData(
            nameFn = { "Should apply ${it.data} on ${it.expression} result in ${it.result}" },
            // given
            ts = listOf(
                TestInput(
                    expression = mapOf("merge" to null),
                    result = listOf(null)
                ),
                TestInput(
                    expression = mapOf("merge" to listOf(1, listOf(2, listOf(3,4)))),
                    result = listOf(1, 2, listOf(3,4))
                ),
                TestInput(
                    expression = mapOf("merge" to listOf(1, listOf(2))),
                    result = listOf(1, 2)
                ),
                TestInput(
                    expression = mapOf("merge" to listOf(1, 2)),
                    result = listOf(1, 2)
                ),
                TestInput(
                    expression = mapOf("merge" to 1),
                    result = listOf(1)
                ),
                TestInput(
                    expression = mapOf("merge" to listOf(listOf(1), listOf(2, 3))),
                    result = listOf(1, 2, 3)
                ),
                TestInput(
                    expression = mapOf("merge" to listOf(listOf(1, 2), listOf(3))),
                    result = listOf(1, 2, 3)
                ),
                TestInput(
                    expression = mapOf("merge" to listOf(listOf(1), listOf(2), listOf(3))),
                    result = listOf(1, 2, 3)
                ),
                TestInput(
                    expression = mapOf("merge" to listOf(listOf(1), listOf(2))),
                    result = listOf(1, 2)
                ),
                TestInput(
                    expression = mapOf("merge" to listOf(listOf(1), emptyList())),
                    result = listOf(1)
                ),
                TestInput(
                    expression = mapOf("merge" to listOf(listOf(1))),
                    result = listOf(1)
                ),
                TestInput(
                    expression = mapOf("merge" to emptyList<Any>()),
                    result = emptyList<Any>()
                ),
                TestInput(
                    expression = mapOf("merge" to listOf(1, 2, listOf(3, 4))),
                    result = listOf(1, 2, 3, 4)
                ),
                TestInput(
                    expression = mapOf("merge" to listOf(listOf(1, 2), listOf(3, 4))),
                    result = listOf(1, 2, 3, 4)
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
