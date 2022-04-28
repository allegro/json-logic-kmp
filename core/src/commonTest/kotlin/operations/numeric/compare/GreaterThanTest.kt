package operations.numeric.compare

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import testWithInputData

class GreaterThanTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with GreaterThan operation") {
        testWithInputData(
            logicEngine = logicEngine,
            data = listOf(
                TestInput(
                    expression = mapOf(">" to listOf(2, 1)),
                    resultValue = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(">" to listOf(1, 1)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">" to listOf(1, 2)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">" to listOf("2", 1)),
                    resultValue = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(">" to listOf("2", "banana")),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">" to listOf(1, "banana")),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">" to listOf(1, 2, 3)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">" to listOf(1, 2, 3, 4)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">" to listOf(1, 1, 3)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">" to listOf(1, 1, 3, 0)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">" to listOf(1, 4, 3)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">" to listOf(4, 1, 3)),
                    resultValue = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(">" to listOf(4, 3, 1)),
                    resultValue = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(">" to listOf("4", 3, "1")),
                    resultValue = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(">" to listOf("banana", 3, "1")),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">" to listOf(4, "3", 1)),
                    resultValue = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(">" to listOf(0, mapOf("var" to "temp"), 100)),
                    data = mapOf("temp" to 37),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">" to listOf("apple", "banana")),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">" to listOf("grapes", "banana")),
                    resultValue = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(">" to listOf("grapes", true)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">" to listOf(1, false)),
                    resultValue = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(">" to listOf(-1, false)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">" to listOf(false, 3, 3)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">" to listOf("banana", 3, 3)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">" to listOf(listOf(1, 2, 3), listOf(3))),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">" to listOf("true", true)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">" to listOf("true", false)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">" to listOf("true", "false")),
                    resultValue = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(">" to listOf(true, false)),
                    resultValue = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(">" to listOf(true, "false")),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">" to listOf(false, "false")),
                    resultValue = JsonLogicResult.Success(false)
                ),
            )
        )
    }
})
