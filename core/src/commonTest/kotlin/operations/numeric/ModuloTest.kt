package operations.numeric

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import valueShouldBe

class ModuloTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    withData(
        nameFn = { input -> "Should evaluated ${input.expression} with given ${input.data} result in ${input.result}" },
        ts = listOf(
            TestInput(
                expression = mapOf("%" to listOf(1, 2)),
                result = JsonLogicResult.Success(1)
            ),
            TestInput(
                expression = mapOf("%" to listOf(1, 2, 5)),
                result = JsonLogicResult.Success(1)
            ),
            TestInput(
                expression = mapOf("%" to listOf(2, 2)),
                result = JsonLogicResult.Success(0)
            ),
            TestInput(
                expression = mapOf("%" to listOf(3, 2)),
                result = JsonLogicResult.Success(1)
            ),
            TestInput(
                expression = mapOf("%" to listOf("3.5", 1.2)),
                result = JsonLogicResult.Success(1.1)
            ),
            TestInput(
                expression = mapOf("%" to listOf(0, 1.2)),
                result = JsonLogicResult.Success(0)
            ),
            TestInput(
                expression = mapOf("%" to listOf("2", 1.5, "banana")),
                result = JsonLogicResult.Success(0.5)
            ),
            TestInput(
                expression = mapOf("%" to listOf("2", 2.5, listOf("banana"))),
                result = JsonLogicResult.Success(2)
            ),
            TestInput(
                expression = mapOf("%" to listOf(null, 5)),
                result = JsonLogicResult.Success(0)
            ),
            TestInput(
                expression = mapOf("%" to listOf(false, true)),
                result = JsonLogicResult.Success(0)
            ),
            TestInput(
                expression = mapOf("%" to listOf(0, true)),
                result = JsonLogicResult.Success(0)
            ),
            TestInput(
                expression = mapOf("%" to listOf(emptyList<String>(), 2)),
                result = JsonLogicResult.Success(0)
            ),
            TestInput(
                expression = mapOf("%" to listOf(1, true)),
                result = JsonLogicResult.Success(0)
            ),
            TestInput(
                expression = mapOf("%" to listOf(listOf("1"), listOf("2"), listOf("3"))),
                result = JsonLogicResult.Success(1)
            ),
            TestInput(
                expression = mapOf("%" to listOf(listOf("2"), 3)),
                result = JsonLogicResult.Success(2)
            ),
            TestInput(
                expression = mapOf("%" to listOf(listOf(listOf("5")), 6)),
                result = JsonLogicResult.Success(5)
            ),
            TestInput(
                expression = mapOf("%" to listOf(listOf(listOf("5")), listOf(6))),
                result = JsonLogicResult.Success(5)
            ),
            TestInput(
                expression = mapOf("%" to listOf(Int.MAX_VALUE + 3L, 3)),
                result = JsonLogicResult.Success((Int.MAX_VALUE + 3L).mod(3))
            ),
            TestInput(
                expression = mapOf("%" to listOf(Int.MIN_VALUE - 3L, -3)),
                result = JsonLogicResult.Success((Int.MIN_VALUE - 3L).mod(-3))
            ),
            TestInput(
                expression = mapOf("%" to listOf(listOf(listOf("5"), listOf(6)))),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf("%" to listOf(listOf("5", listOf("5")), listOf("5"), listOf("5"))),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(expression = mapOf("%" to listOf(2)), result = JsonLogicResult.Failure.NullResult),
            TestInput(expression = mapOf("%" to listOf("a", 2)), result = JsonLogicResult.Failure.NullResult),
            TestInput(
                expression = mapOf("%" to listOf(listOf(2, "a"), 2)),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf("%" to listOf(listOf("a", 2), 2)),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf("%" to listOf(listOf(2, 2), 2)),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf("%" to listOf(listOf(2, "a"), listOf("a", 2))),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(expression = mapOf("%" to listOf(2, null)), result = JsonLogicResult.Failure.NullResult),
            TestInput(
                expression = mapOf("%" to listOf(null, null)),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(expression = mapOf("%" to listOf(null)), result = JsonLogicResult.Failure.NullResult),
            TestInput(expression = mapOf("%" to listOf("banana")), result = JsonLogicResult.Failure.NullResult),
            TestInput(
                expression = mapOf("%" to listOf(true, false)),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(expression = mapOf("%" to listOf(true)), result = JsonLogicResult.Failure.NullResult),
            TestInput(expression = mapOf("%" to listOf(false)), result = JsonLogicResult.Failure.NullResult),
            TestInput(
                expression = mapOf("%" to listOf(true, null)),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf("%" to listOf(false, null)),
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
