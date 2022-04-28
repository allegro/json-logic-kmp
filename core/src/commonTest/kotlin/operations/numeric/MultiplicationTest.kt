package operations.numeric

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import testWithInputData

class MultiplicationTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with Multiplication operation") {
        testWithInputData(
            logicEngine = logicEngine,
            data = listOf(
                TestInput(
                    expression = mapOf("*" to listOf(3, 2)),
                    resultValue = JsonLogicResult.Success(6)
                ),
                TestInput(
                    expression = mapOf("*" to listOf(2, 2, 2)),
                    resultValue = JsonLogicResult.Success(8)
                ),
                TestInput(
                    expression = mapOf("*" to listOf(1)),
                    resultValue = JsonLogicResult.Success(1)
                ),
                TestInput(
                    expression = mapOf("*" to listOf("1", 1)),
                    resultValue = JsonLogicResult.Success(1)
                ),
                TestInput(
                    expression = mapOf("*" to listOf("1.7", 3)),
                    resultValue = JsonLogicResult.Success(5.1)
                ),
                TestInput(
                    expression = mapOf("*" to listOf(2, 0)),
                    resultValue = JsonLogicResult.Success(0)
                ),
                TestInput(
                    expression = mapOf("*" to listOf(0, 2)),
                    resultValue = JsonLogicResult.Success(0)
                ),
                TestInput(
                    expression = mapOf("*" to listOf("banana")),
                    resultValue = JsonLogicResult.Success("banana")
                ),
                TestInput(
                    expression = mapOf("*" to listOf(true)),
                    resultValue = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf("*" to listOf(1)),
                    resultValue = JsonLogicResult.Success(1)
                ),
                TestInput(
                    expression = mapOf("*" to listOf(true)),
                    resultValue = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf("*" to listOf(false)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("*" to listOf(listOf(2, "a"), 2)),
                    resultValue = JsonLogicResult.Success(4)
                ),
                TestInput(
                    expression = mapOf("*" to listOf(listOf(2, 2), 2)),
                    resultValue = JsonLogicResult.Success(4)
                ),
                TestInput(
                    expression = mapOf("*" to listOf(listOf("5"), listOf("5"), listOf("5"))),
                    resultValue = JsonLogicResult.Success(125)
                ),
                TestInput(
                    expression = mapOf("*" to listOf(listOf("5", listOf("5")), listOf("5"), listOf("5"))),
                    resultValue = JsonLogicResult.Success(125)
                ),
                TestInput(
                    expression = mapOf("*" to listOf(listOf("5"), 6)),
                    resultValue = JsonLogicResult.Success(30)
                ),
                TestInput(
                    expression = mapOf("*" to listOf(listOf(listOf("5")), 6)),
                    resultValue = JsonLogicResult.Success(30)
                ),
                TestInput(
                    expression = mapOf("*" to listOf(listOf(listOf("5")), listOf(6))),
                    resultValue = JsonLogicResult.Success(30)
                ),
                TestInput(
                    expression = mapOf("*" to listOf(listOf(listOf("5"), listOf(6)))),
                    resultValue = JsonLogicResult.Success(listOf(listOf("5"), listOf(6)))
                ),
                TestInput(
                    expression = mapOf("*" to listOf(emptyList<String>(), 2)),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf("*" to listOf("2", 1.5, "banana")),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf("*" to listOf("2", 1.5, listOf("banana"))),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(expression = mapOf("*" to listOf(null, 5)), resultValue = JsonLogicResult.NullResultFailure),
                TestInput(expression = mapOf("*" to listOf(2, null)), resultValue = JsonLogicResult.NullResultFailure),
                TestInput(
                    expression = mapOf("*" to listOf(null, null)),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(expression = mapOf("*" to listOf(null)), resultValue = JsonLogicResult.NullResultFailure),
                TestInput(
                    expression = mapOf("*" to listOf(listOf("a", 2), 2)),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf("*" to listOf(listOf(2, "a"), listOf("a", 2))),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf("*" to listOf(true, null)),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf("*" to listOf(false, null)),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf("*" to listOf(false, true)),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(expression = mapOf("*" to listOf(0, true)), resultValue = JsonLogicResult.NullResultFailure),
                TestInput(expression = mapOf("*" to listOf(1, true)), resultValue = JsonLogicResult.NullResultFailure),
                TestInput(expression = mapOf("*" to listOf("a", 2)), resultValue = JsonLogicResult.NullResultFailure),
                TestInput(
                    expression = mapOf("*" to listOf(true, false)),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
            )
        )
    }
})

@Suppress("unused")
private val defectiveTestCases = listOf(
    TestInput(
        expression = mapOf("*" to listOf("1.3", "3.7")),
        resultValue = JsonLogicResult.Success(4.81)
    ),
)
