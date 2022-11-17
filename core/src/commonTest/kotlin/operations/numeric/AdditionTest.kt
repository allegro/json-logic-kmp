package operations.numeric

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import valueShouldBe

class AdditionTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    withData(
        nameFn = { input -> "Should evaluated ${input.expression} with given ${input.data} result in ${input.result}" },
        ts = listOf(
            TestInput(
                expression = mapOf("+" to listOf(1, 2)),
                result = JsonLogicResult.Success(3)
            ),
            TestInput(
                expression = mapOf("+" to listOf(listOf("5"), listOf("5"), listOf("5"))),
                result = JsonLogicResult.Success(15)
            ),
            TestInput(
                expression = mapOf("+" to listOf(listOf("5", listOf("5")), listOf("5"), listOf("5"))),
                result = JsonLogicResult.Success(15)
            ),
            TestInput(
                expression = mapOf("+" to listOf(listOf("5"), 6)),
                result = JsonLogicResult.Success(11)
            ),
            TestInput(
                expression = mapOf("+" to listOf(listOf(listOf("5")), 6)),
                result = JsonLogicResult.Success(11)
            ),
            TestInput(
                expression = mapOf("+" to listOf(listOf(listOf("5")), listOf(6))),
                result = JsonLogicResult.Success(11)
            ),
            TestInput(
                expression = mapOf("+" to listOf(listOf(listOf("5"), listOf(6)))),
                result = JsonLogicResult.Success(5)
            ),
            TestInput(
                expression = mapOf("+" to listOf(listOf("5"), listOf("6"))),
                result = JsonLogicResult.Success(11)
            ),
            TestInput(
                expression = mapOf("+" to listOf(2, 2, 2)),
                result = JsonLogicResult.Success(6)
            ),
            TestInput(
                expression = mapOf("+" to listOf(1)),
                result = JsonLogicResult.Success(1)
            ),
            TestInput(
                expression = mapOf("+" to emptyList<Double>()),
                result = JsonLogicResult.Success(0)
            ),
            TestInput(
                expression = mapOf("+" to listOf("1", 1)),
                result = JsonLogicResult.Success(2)
            ),
            TestInput(
                expression = mapOf("+" to listOf("1", 1.5)),
                result = JsonLogicResult.Success(2.5)
            ),
            TestInput(
                expression = mapOf("+" to listOf(12.6543534, 1.1)),
                result = JsonLogicResult.Success(13.7543534)
            ),
            TestInput(
                expression = mapOf("+" to listOf(9, .9, .09, .009, .0009, .00009)),
                result = JsonLogicResult.Success(9.99999)
            ),
            TestInput(
                expression = mapOf("+" to listOf(Int.MAX_VALUE, 3)),
                result = JsonLogicResult.Success(Int.MAX_VALUE + 3L)
            ),
            TestInput(
                expression = mapOf("+" to listOf(Int.MIN_VALUE, -3)),
                result = JsonLogicResult.Success(Int.MIN_VALUE - 3L)
            ),
            TestInput(
                expression = mapOf("+" to listOf(listOf(2, 2), 2)),
                result = JsonLogicResult.Success(4)
            ),
            TestInput(
                expression = mapOf("+" to listOf(listOf(2, "a"), 2)),
                result = JsonLogicResult.Success(4)
            ),
            TestInput(
                expression = mapOf("+" to listOf(2, 2, null)),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf("+" to listOf("1", 1.5, "banana")),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf("+" to listOf("1", 1, listOf("banana"))),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(expression = mapOf("+" to listOf("a", 2)), result = JsonLogicResult.Failure.NullResult),
            TestInput(
                expression = mapOf("+" to listOf(listOf("a", 2), 2)),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf("+" to listOf(listOf(2, "a"), listOf("a", 2))),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(expression = mapOf("+" to listOf(null, 5)), result = JsonLogicResult.Failure.NullResult),
            TestInput(expression = mapOf("+" to listOf(5, null)), result = JsonLogicResult.Failure.NullResult),
            TestInput(
                expression = mapOf("+" to listOf(null, null)),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(expression = mapOf("+" to listOf(null)), result = JsonLogicResult.Failure.NullResult),
            TestInput(
                expression = mapOf("+" to listOf(true, false)),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(expression = mapOf("+" to listOf(true)), result = JsonLogicResult.Failure.NullResult),
            TestInput(expression = mapOf("+" to listOf(false)), result = JsonLogicResult.Failure.NullResult),
            TestInput(
                expression = mapOf("+" to listOf(true, null)),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf("+" to listOf(false, null)),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf("+" to listOf(false, true)),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(expression = mapOf("+" to listOf(0, true)), result = JsonLogicResult.Failure.NullResult),
            TestInput(expression = mapOf("+" to listOf(1, true)), result = JsonLogicResult.Failure.NullResult),
            TestInput(
                expression = mapOf("+" to listOf(emptyList<String>(), 2)),
                result = JsonLogicResult.Failure.NullResult
            ),
        )
        // given
    ) { testInput: TestInput ->
        // when
        val evaluationResult = logicEngine.evaluate(testInput.expression, testInput.data)

        // then
        evaluationResult valueShouldBe testInput.result
    }
})
