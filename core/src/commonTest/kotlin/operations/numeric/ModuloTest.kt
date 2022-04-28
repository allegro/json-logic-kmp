package operations.numeric

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import testWithInputData

class ModuloTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with Modulo operation") {
        testWithInputData(
            logicEngine = logicEngine,
            data = listOf(
                TestInput(
                    expression = mapOf("%" to listOf(1, 2)),
                    resultValue = JsonLogicResult.Success(1)
                ),
                TestInput(
                    expression = mapOf("%" to listOf(1, 2, 5)),
                    resultValue = JsonLogicResult.Success(1)
                ),
                TestInput(
                    expression = mapOf("%" to listOf(2, 2)),
                    resultValue = JsonLogicResult.Success(0)
                ),
                TestInput(
                    expression = mapOf("%" to listOf(3, 2)),
                    resultValue = JsonLogicResult.Success(1)
                ),
                TestInput(
                    expression = mapOf("%" to listOf("3.5", 1.2)),
                    resultValue = JsonLogicResult.Success(1.1)
                ),
                TestInput(
                    expression = mapOf("%" to listOf(0, 1.2)),
                    resultValue = JsonLogicResult.Success(0)
                ),
                TestInput(
                    expression = mapOf("%" to listOf("2", 1.5, "banana")),
                    resultValue = JsonLogicResult.Success(0.5)
                ),
                TestInput(
                    expression = mapOf("%" to listOf("2", 2.5, listOf("banana"))),
                    resultValue = JsonLogicResult.Success(2)
                ),
                TestInput(
                    expression = mapOf("%" to listOf(null, 5)),
                    resultValue = JsonLogicResult.Success(0)
                ),
                TestInput(
                    expression = mapOf("%" to listOf(false, true)),
                    resultValue = JsonLogicResult.Success(0)
                ),
                TestInput(
                    expression = mapOf("%" to listOf(0, true)),
                    resultValue = JsonLogicResult.Success(0)
                ),
                TestInput(
                    expression = mapOf("%" to listOf(emptyList<String>(), 2)),
                    resultValue = JsonLogicResult.Success(0)
                ),
                TestInput(
                    expression = mapOf("%" to listOf(1, true)),
                    resultValue = JsonLogicResult.Success(0)
                ),
                TestInput(
                    expression = mapOf("%" to listOf(listOf("1"), listOf("2"), listOf("3"))),
                    resultValue = JsonLogicResult.Success(1)
                ),
                TestInput(
                    expression = mapOf("%" to listOf(listOf("2"), 3)),
                    resultValue = JsonLogicResult.Success(2)
                ),
                TestInput(
                    expression = mapOf("%" to listOf(listOf(listOf("5")), 6)),
                    resultValue = JsonLogicResult.Success(5)
                ),
                TestInput(
                    expression = mapOf("%" to listOf(listOf(listOf("5")), listOf(6))),
                    resultValue = JsonLogicResult.Success(5)
                ),
                TestInput(
                    expression = mapOf("%" to listOf(listOf(listOf("5"), listOf(6)))),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf("%" to listOf(listOf("5", listOf("5")), listOf("5"), listOf("5"))),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(expression = mapOf("%" to listOf(2)), resultValue = JsonLogicResult.NullResultFailure),
                TestInput(expression = mapOf("%" to listOf("a", 2)), resultValue = JsonLogicResult.NullResultFailure),
                TestInput(
                    expression = mapOf("%" to listOf(listOf(2, "a"), 2)),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf("%" to listOf(listOf("a", 2), 2)),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf("%" to listOf(listOf(2, 2), 2)),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf("%" to listOf(listOf(2, "a"), listOf("a", 2))),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(expression = mapOf("%" to listOf(2, null)), resultValue = JsonLogicResult.NullResultFailure),
                TestInput(
                    expression = mapOf("%" to listOf(null, null)),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(expression = mapOf("%" to listOf(null)), resultValue = JsonLogicResult.NullResultFailure),
                TestInput(expression = mapOf("%" to listOf("banana")), resultValue = JsonLogicResult.NullResultFailure),
                TestInput(
                    expression = mapOf("%" to listOf(true, false)),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(expression = mapOf("%" to listOf(true)), resultValue = JsonLogicResult.NullResultFailure),
                TestInput(expression = mapOf("%" to listOf(false)), resultValue = JsonLogicResult.NullResultFailure),
                TestInput(
                    expression = mapOf("%" to listOf(true, null)),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf("%" to listOf(false, null)),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
            )
        )
    }
})

@Suppress("unused")
private val defectiveTestCases = listOf(
    TestInput(
        expression = mapOf("%" to listOf(3.5, 1.3)),
        resultValue = JsonLogicResult.Success(0.9)
    )
)
