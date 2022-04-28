package operations.numeric

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import testWithInputData

class MaxTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with Max operation") {
        testWithInputData(
            logicEngine = logicEngine,
            data = listOf(
                TestInput(
                    expression = mapOf("max" to listOf(1, 2, 3)),
                    resultValue = JsonLogicResult.Success(3)
                ),
                TestInput(
                    expression = mapOf("max" to listOf(1, 3, 3)),
                    resultValue = JsonLogicResult.Success(3)
                ),
                TestInput(
                    expression = mapOf("max" to listOf("-1", -2, "-3")),
                    resultValue = JsonLogicResult.Success(-1)
                ),
                TestInput(
                    expression = mapOf("max" to listOf(3, 2, 1)),
                    resultValue = JsonLogicResult.Success(3)
                ),
                TestInput(
                    expression = mapOf("max" to listOf(1)),
                    resultValue = JsonLogicResult.Success(1)
                ),
                TestInput(
                    expression = mapOf("max" to listOf(1, "banana")),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf("max" to listOf(1, "banana", listOf(1, 2))),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf("max" to listOf(1, "2")),
                    resultValue = JsonLogicResult.Success(2)
                ),
            )
        )
    }
})
