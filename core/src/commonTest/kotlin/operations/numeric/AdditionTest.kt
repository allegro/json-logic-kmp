package operations.numeric

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import testWithInputData

class AdditionTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with Addition operation") {
        testWithInputData(
            logicEngine = logicEngine,
            data = listOf(
                TestInput(
                    expression = mapOf("+" to listOf(1, 2)),
                    resultValue = JsonLogicResult.Success(3)
                ),
                TestInput(
                    expression = mapOf("+" to listOf(listOf("5"), listOf("5"), listOf("5"))),
                    resultValue = JsonLogicResult.Success(15)
                ),
                TestInput(
                    expression = mapOf("+" to listOf(listOf("5", listOf("5")), listOf("5"), listOf("5"))),
                    resultValue = JsonLogicResult.Success(15)
                ),
                TestInput(
                    expression = mapOf("+" to listOf(listOf("5"), 6)),
                    resultValue = JsonLogicResult.Success(11)
                ),
                TestInput(
                    expression = mapOf("+" to listOf(listOf(listOf("5")), 6)),
                    resultValue = JsonLogicResult.Success(11)
                ),
                TestInput(
                    expression = mapOf("+" to listOf(listOf(listOf("5")), listOf(6))),
                    resultValue = JsonLogicResult.Success(11)
                ),
                TestInput(
                    expression = mapOf("+" to listOf(listOf(listOf("5"), listOf(6)))),
                    resultValue = JsonLogicResult.Success(5)
                ),
                TestInput(
                    expression = mapOf("+" to listOf(listOf("5"), listOf("6"))),
                    resultValue = JsonLogicResult.Success(11)
                ),
                TestInput(
                    expression = mapOf("+" to listOf(2, 2, 2)),
                    resultValue = JsonLogicResult.Success(6)
                ),
                TestInput(
                    expression = mapOf("+" to listOf(1)),
                    resultValue = JsonLogicResult.Success(1)
                ),
                TestInput(
                    expression = mapOf("+" to emptyList<Double>()),
                    resultValue = JsonLogicResult.Success(0)
                ),
                TestInput(
                    expression = mapOf("+" to listOf("1", 1)),
                    resultValue = JsonLogicResult.Success(2)
                ),
                TestInput(
                    expression = mapOf("+" to listOf("1", 1.5)),
                    resultValue = JsonLogicResult.Success(2.5)
                ),
                TestInput(
                    expression = mapOf("+" to listOf(12.6543534, 1.1)),
                    resultValue = JsonLogicResult.Success(13.7543534)
                ),
                TestInput(
                    expression = mapOf("+" to listOf(9, .9, .09, .009, .0009, .00009)),
                    resultValue = JsonLogicResult.Success(9.99999)
                ),
                TestInput(
                    expression = mapOf("+" to listOf(listOf(2, 2), 2)),
                    resultValue = JsonLogicResult.Success(4)
                ),
                TestInput(
                    expression = mapOf("+" to listOf(listOf(2, "a"), 2)),
                    resultValue = JsonLogicResult.Success(4)
                ),
                TestInput(
                    expression = mapOf("+" to listOf(2, 2, null)),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf("+" to listOf("1", 1.5, "banana")),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf("+" to listOf("1", 1, listOf("banana"))),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(expression = mapOf("+" to listOf("a", 2)), resultValue = JsonLogicResult.NullResultFailure),
                TestInput(
                    expression = mapOf("+" to listOf(listOf("a", 2), 2)),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf("+" to listOf(listOf(2, "a"), listOf("a", 2))),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(expression = mapOf("+" to listOf(null, 5)), resultValue = JsonLogicResult.NullResultFailure),
                TestInput(expression = mapOf("+" to listOf(5, null)), resultValue = JsonLogicResult.NullResultFailure),
                TestInput(
                    expression = mapOf("+" to listOf(null, null)),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(expression = mapOf("+" to listOf(null)), resultValue = JsonLogicResult.NullResultFailure),
                TestInput(
                    expression = mapOf("+" to listOf(true, false)),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(expression = mapOf("+" to listOf(true)), resultValue = JsonLogicResult.NullResultFailure),
                TestInput(expression = mapOf("+" to listOf(false)), resultValue = JsonLogicResult.NullResultFailure),
                TestInput(
                    expression = mapOf("+" to listOf(true, null)),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf("+" to listOf(false, null)),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf("+" to listOf(false, true)),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(expression = mapOf("+" to listOf(0, true)), resultValue = JsonLogicResult.NullResultFailure),
                TestInput(expression = mapOf("+" to listOf(1, true)), resultValue = JsonLogicResult.NullResultFailure),
                TestInput(
                    expression = mapOf("+" to listOf(emptyList<String>(), 2)),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
            )
        )
    }
})
