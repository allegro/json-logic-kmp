package operations.numeric

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import testWithInputData

class MinTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with Min operation") {
        testWithInputData(
            logicEngine = logicEngine,
            data = listOf(
                TestInput(
                    expression = mapOf("min" to listOf(1, 2, 3)),
                    resultValue = JsonLogicResult.Success(1)
                ),
                TestInput(
                    expression = mapOf("min" to listOf("1", "0.2", 0.3)),
                    resultValue = JsonLogicResult.Success(0.2)
                ),
                TestInput(
                    expression = mapOf("min" to listOf("-2", "0.2", 0.3)),
                    resultValue = JsonLogicResult.Success(-2)
                ),
                TestInput(
                    expression = mapOf("min" to listOf(1, 3, 3)),
                    resultValue = JsonLogicResult.Success(1)
                ),
                TestInput(
                    expression = mapOf("min" to listOf(3, 2, 1)),
                    resultValue = JsonLogicResult.Success(1)
                ),
                TestInput(
                    expression = mapOf("min" to listOf(1)),
                    resultValue = JsonLogicResult.Success(1)
                ),
                TestInput(
                    expression = mapOf("min" to listOf("1", 2)),
                    resultValue = JsonLogicResult.Success(1)
                ),
                TestInput(
                    expression = mapOf("min" to listOf(1, "banana")),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf("min" to listOf(1, "banana", listOf(1, 2))),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
            )
        )
    }
})
