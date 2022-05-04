package operations.numeric

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import valueShouldBe

class MultiplicationTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    withData(
        nameFn = { input -> "Should evaluated ${input.expression} with given ${input.data} result in ${input.result}" },
        ts = listOf(
            TestInput(
                expression = mapOf("*" to listOf(3, 2)),
                result = JsonLogicResult.Success(6)
            ),
            TestInput(
                expression = mapOf("*" to listOf(2, 2, 2)),
                result = JsonLogicResult.Success(8)
            ),
            TestInput(
                expression = mapOf("*" to listOf(1)),
                result = JsonLogicResult.Success(1)
            ),
            TestInput(
                expression = mapOf("*" to listOf("1", 1)),
                result = JsonLogicResult.Success(1)
            ),
            TestInput(
                expression = mapOf("*" to listOf("1.7", 3)),
                result = JsonLogicResult.Success(5.1)
            ),
            TestInput(
                expression = mapOf("*" to listOf(2, 0)),
                result = JsonLogicResult.Success(0)
            ),
            TestInput(
                expression = mapOf("*" to listOf(0, 2)),
                result = JsonLogicResult.Success(0)
            ),
            TestInput(
                expression = mapOf("*" to listOf("banana")),
                result = JsonLogicResult.Success("banana")
            ),
            TestInput(
                expression = mapOf("*" to listOf(true)),
                result = JsonLogicResult.Success(true)
            ),
            TestInput(
                expression = mapOf("*" to listOf(1)),
                result = JsonLogicResult.Success(1)
            ),
            TestInput(
                expression = mapOf("*" to listOf(true)),
                result = JsonLogicResult.Success(true)
            ),
            TestInput(
                expression = mapOf("*" to listOf(false)),
                result = JsonLogicResult.Success(false)
            ),
            TestInput(
                expression = mapOf("*" to listOf(listOf(2, "a"), 2)),
                result = JsonLogicResult.Success(4)
            ),
            TestInput(
                expression = mapOf("*" to listOf(listOf(2, 2), 2)),
                result = JsonLogicResult.Success(4)
            ),
            TestInput(
                expression = mapOf("*" to listOf(listOf("5"), listOf("5"), listOf("5"))),
                result = JsonLogicResult.Success(125)
            ),
            TestInput(
                expression = mapOf("*" to listOf(listOf("5", listOf("5")), listOf("5"), listOf("5"))),
                result = JsonLogicResult.Success(125)
            ),
            TestInput(
                expression = mapOf("*" to listOf(listOf("5"), 6)),
                result = JsonLogicResult.Success(30)
            ),
            TestInput(
                expression = mapOf("*" to listOf(listOf(listOf("5")), 6)),
                result = JsonLogicResult.Success(30)
            ),
            TestInput(
                expression = mapOf("*" to listOf(listOf(listOf("5")), listOf(6))),
                result = JsonLogicResult.Success(30)
            ),
            TestInput(
                expression = mapOf("*" to listOf(listOf(listOf("5"), listOf(6)))),
                result = JsonLogicResult.Success(listOf(listOf("5"), listOf(6)))
            ),
            TestInput(
                expression = mapOf("*" to listOf(emptyList<String>(), 2)),
                result = JsonLogicResult.NullResultFailure
            ),
            TestInput(
                expression = mapOf("*" to listOf("2", 1.5, "banana")),
                result = JsonLogicResult.NullResultFailure
            ),
            TestInput(
                expression = mapOf("*" to listOf("2", 1.5, listOf("banana"))),
                result = JsonLogicResult.NullResultFailure
            ),
            TestInput(expression = mapOf("*" to listOf(null, 5)), result = JsonLogicResult.NullResultFailure),
            TestInput(expression = mapOf("*" to listOf(2, null)), result = JsonLogicResult.NullResultFailure),
            TestInput(
                expression = mapOf("*" to listOf(null, null)),
                result = JsonLogicResult.NullResultFailure
            ),
            TestInput(expression = mapOf("*" to listOf(null)), result = JsonLogicResult.NullResultFailure),
            TestInput(
                expression = mapOf("*" to listOf(listOf("a", 2), 2)),
                result = JsonLogicResult.NullResultFailure
            ),
            TestInput(
                expression = mapOf("*" to listOf(listOf(2, "a"), listOf("a", 2))),
                result = JsonLogicResult.NullResultFailure
            ),
            TestInput(
                expression = mapOf("*" to listOf(true, null)),
                result = JsonLogicResult.NullResultFailure
            ),
            TestInput(
                expression = mapOf("*" to listOf(false, null)),
                result = JsonLogicResult.NullResultFailure
            ),
            TestInput(
                expression = mapOf("*" to listOf(false, true)),
                result = JsonLogicResult.NullResultFailure
            ),
            TestInput(expression = mapOf("*" to listOf(0, true)), result = JsonLogicResult.NullResultFailure),
            TestInput(expression = mapOf("*" to listOf(1, true)), result = JsonLogicResult.NullResultFailure),
            TestInput(expression = mapOf("*" to listOf("a", 2)), result = JsonLogicResult.NullResultFailure),
            TestInput(
                expression = mapOf("*" to listOf(true, false)),
                result = JsonLogicResult.NullResultFailure
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

@Suppress("unused")
private val defectiveTestCases = listOf(
    TestInput(
        expression = mapOf("*" to listOf("1.3", "3.7")),
        result = JsonLogicResult.Success(4.81)
    ),
)
