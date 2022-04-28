package operations.numeric

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import testWithInputData

class DivisionTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with Division operation") {
        testWithInputData(
            logicEngine = logicEngine,
            data = listOf(
                TestInput(
                    expression = mapOf("/" to listOf(4, 2)),
                    result = JsonLogicResult.Success(2)
                ),
                TestInput(
                    expression = mapOf("/" to listOf(2, 4)),
                    result = JsonLogicResult.Success(0.5)
                ),
                TestInput(
                    expression = mapOf("/" to listOf("1", 1)),
                    result = JsonLogicResult.Success(1)
                ),
                TestInput(
                    expression = mapOf("/" to listOf(0, 1)),
                    result = JsonLogicResult.Success(0)
                ),
                TestInput(
                    expression = mapOf("/" to listOf("2.5", "2")),
                    result = JsonLogicResult.Success(1.25)
                ),
                TestInput(
                    expression = mapOf("/" to listOf("2.5", "2", "3", 5)),
                    result = JsonLogicResult.Success(1.25)
                ),
                TestInput(
                    expression = mapOf("/" to listOf(listOf("2.5"), 2)),
                    result = JsonLogicResult.Success(1.25)
                ),
                TestInput(
                    expression = mapOf("/" to listOf(null, 5)),
                    result = JsonLogicResult.Success(0)
                ),
                TestInput(
                    expression = mapOf("/" to listOf(false, true)),
                    result = JsonLogicResult.Success(0)
                ),
                TestInput(
                    expression = mapOf("/" to listOf(0, true)),
                    result = JsonLogicResult.Success(0)
                ),
                TestInput(
                    expression = mapOf("/" to listOf(1, true)),
                    result = JsonLogicResult.Success(1)
                ),
                TestInput(
                    expression = mapOf("/" to listOf(listOf("5"), listOf("5"), listOf("5"))),
                    result = JsonLogicResult.Success(1)
                ),
                TestInput(
                    expression = mapOf("/" to listOf(listOf("5"), 5)),
                    result = JsonLogicResult.Success(1)
                ),
                TestInput(
                    expression = mapOf("/" to listOf(listOf(listOf("5")), 5)),
                    result = JsonLogicResult.Success(1)
                ),
                TestInput(
                    expression = mapOf("/" to listOf(listOf(listOf("5")), listOf(5))),
                    result = JsonLogicResult.Success(1)
                ),
                TestInput(
                    expression = mapOf("/" to listOf(emptyList<String>(), 2)),
                    result = JsonLogicResult.Success(0)
                ),
                TestInput(expression = mapOf("/" to listOf("1", "0")), result = JsonLogicResult.NullResultFailure),
                TestInput(expression = mapOf("/" to listOf("1", 0)), result = JsonLogicResult.NullResultFailure),
                TestInput(
                    expression = mapOf("/" to listOf("2.5", listOf("2", "3", 5))),
                    result = JsonLogicResult.NullResultFailure
                ),
                TestInput(expression = mapOf("/" to listOf(2, null)), result = JsonLogicResult.NullResultFailure),
                TestInput(
                    expression = mapOf("/" to listOf(null, null)),
                    result = JsonLogicResult.NullResultFailure
                ),
                TestInput(expression = mapOf("/" to listOf(null)), result = JsonLogicResult.NullResultFailure),
                TestInput(expression = mapOf("/" to listOf("banana")), result = JsonLogicResult.NullResultFailure),
                TestInput(
                    expression = mapOf("/" to listOf(true, false)),
                    result = JsonLogicResult.NullResultFailure
                ),
                TestInput(expression = mapOf("/" to listOf(true)), result = JsonLogicResult.NullResultFailure),
                TestInput(expression = mapOf("/" to listOf(false)), result = JsonLogicResult.NullResultFailure),
                TestInput(
                    expression = mapOf("/" to listOf(true, null)),
                    result = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf("/" to listOf(false, null)),
                    result = JsonLogicResult.NullResultFailure
                ),
                TestInput(expression = mapOf("/" to listOf("a", 2)), result = JsonLogicResult.NullResultFailure),
                TestInput(
                    expression = mapOf("/" to listOf(listOf(2, "a"), 2)),
                    result = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf("/" to listOf(listOf("a", 2), 2)),
                    result = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf("/" to listOf(listOf(2, 2), 2)),
                    result = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf("/" to listOf(listOf(2, "a"), listOf("a", 2))),
                    result = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf("/" to listOf(listOf("5", listOf("5")), listOf("5"), listOf("5"))),
                    result = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf("/" to listOf(listOf(listOf("5"), listOf(6)))),
                    result = JsonLogicResult.NullResultFailure
                ),
            )
        )
    }
})
