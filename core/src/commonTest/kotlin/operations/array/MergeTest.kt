package operations.array

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import testWithInputData

class MergeTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with Merge operation") {
        testWithInputData(
            logicEngine = logicEngine,
            data = listOf(
                TestInput(
                    expression = mapOf("merge" to null),
                    result = JsonLogicResult.Success(listOf(null))
                ),
                TestInput(
                    expression = mapOf("merge" to listOf(1, listOf(2, listOf(3, 4)))),
                    result = JsonLogicResult.Success(listOf(1, 2, listOf(3, 4)))
                ),
                TestInput(
                    expression = mapOf("merge" to listOf(1, listOf(2))),
                    result = JsonLogicResult.Success(listOf(1, 2))
                ),
                TestInput(
                    expression = mapOf("merge" to listOf(1, 2)),
                    result = JsonLogicResult.Success(listOf(1, 2))
                ),
                TestInput(
                    expression = mapOf("merge" to 1),
                    result = JsonLogicResult.Success(listOf(1))
                ),
                TestInput(
                    expression = mapOf("merge" to listOf(listOf(1), listOf(2, 3))),
                    result = JsonLogicResult.Success(listOf(1, 2, 3))
                ),
                TestInput(
                    expression = mapOf("merge" to listOf(listOf(1, 2), listOf(3))),
                    result = JsonLogicResult.Success(listOf(1, 2, 3))
                ),
                TestInput(
                    expression = mapOf("merge" to listOf(listOf(1), listOf(2), listOf(3))),
                    result = JsonLogicResult.Success(listOf(1, 2, 3))
                ),
                TestInput(
                    expression = mapOf("merge" to listOf(listOf(1), listOf(2))),
                    result = JsonLogicResult.Success(listOf(1, 2))
                ),
                TestInput(
                    expression = mapOf("merge" to listOf(listOf(1), emptyList())),
                    result = JsonLogicResult.Success(listOf(1))
                ),
                TestInput(
                    expression = mapOf("merge" to listOf(listOf(1))),
                    result = JsonLogicResult.Success(listOf(1))
                ),
                TestInput(
                    expression = mapOf("merge" to emptyList<Any>()),
                    result = JsonLogicResult.Success(emptyList<Any>())
                ),
                TestInput(
                    expression = mapOf("merge" to listOf(1, 2, listOf(3, 4))),
                    result = JsonLogicResult.Success(listOf(1, 2, 3, 4))
                ),
                TestInput(
                    expression = mapOf("merge" to listOf(listOf(1, 2), listOf(3, 4))),
                    result = JsonLogicResult.Success(listOf(1, 2, 3, 4))
                )
            )
        )
    }
})
