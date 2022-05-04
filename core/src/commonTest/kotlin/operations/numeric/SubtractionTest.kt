package operations.numeric

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import valueShouldBe

class SubtractionTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    withData(
        nameFn = { input -> "Should evaluated ${input.expression} with given ${input.data} result in ${input.result}" },
        ts = listOf(
            TestInput(
                expression = mapOf("-" to listOf(2, 3)),
                result = JsonLogicResult.Success(-1)
            ),
            TestInput(
                expression = mapOf("-" to listOf(3, 2)),
                result = JsonLogicResult.Success(1)
            ),
            TestInput(
                expression = mapOf("-" to listOf(3)),
                result = JsonLogicResult.Success(-3)
            ),
            TestInput(
                expression = mapOf("-" to listOf("1", 1)),
                result = JsonLogicResult.Success(0)
            ),
            TestInput(
                expression = mapOf("-" to listOf(0, 1)),
                result = JsonLogicResult.Success(-1)
            ),
            TestInput(
                expression = mapOf("-" to listOf("1.3", 0)),
                result = JsonLogicResult.Success(1.3)
            ),
            TestInput(
                expression = mapOf("-" to listOf("1", 1.5, "banana")),
                result = JsonLogicResult.Success(-0.5)
            ),
            TestInput(
                expression = mapOf("-" to listOf("1", 1, listOf("banana"))),
                result = JsonLogicResult.Success(0.0)
            ),
            TestInput(
                expression = mapOf("-" to listOf(listOf("5"), listOf("5"), listOf("5"))),
                result = JsonLogicResult.Success(0)
            ),
            TestInput(
                expression = mapOf("-" to listOf(listOf("5"), 6)),
                result = JsonLogicResult.Success(-1)
            ),
            TestInput(
                expression = mapOf("-" to listOf(listOf(listOf("5")), 6)),
                result = JsonLogicResult.Success(-1)
            ),
            TestInput(
                expression = mapOf("-" to listOf(listOf(listOf("5")), listOf(6))),
                result = JsonLogicResult.Success(-1)
            ),
            TestInput(
                expression = mapOf("-" to listOf(null, 5)),
                result = JsonLogicResult.Success(-5)
            ),
            TestInput(
                expression = mapOf("-" to listOf(2, null)),
                result = JsonLogicResult.Success(2)
            ),
            TestInput(
                expression = mapOf("-" to listOf(null, null)),
                result = JsonLogicResult.Success(0)
            ),
            TestInput(
                expression = mapOf("-" to listOf(null)),
                result = JsonLogicResult.Success(0)
            ),
            TestInput(
                expression = mapOf("-" to listOf(true, false)),
                result = JsonLogicResult.Success(1)
            ),
            TestInput(
                expression = mapOf("-" to listOf(true)),
                result = JsonLogicResult.Success(-1)
            ),
            TestInput(
                expression = mapOf("-" to listOf(false)),
                result = JsonLogicResult.Success(0)
            ),
            TestInput(
                expression = mapOf("-" to listOf(true, null)),
                result = JsonLogicResult.Success(1)
            ),
            TestInput(
                expression = mapOf("-" to listOf(false, null)),
                result = JsonLogicResult.Success(0)
            ),
            TestInput(
                expression = mapOf("-" to listOf(false, true)),
                result = JsonLogicResult.Success(-1)
            ),
            TestInput(
                expression = mapOf("-" to listOf(0, true)),
                result = JsonLogicResult.Success(-1)
            ),
            TestInput(
                expression = mapOf("-" to listOf(1, true)),
                result = JsonLogicResult.Success(0)
            ),
            TestInput(
                expression = mapOf("-" to listOf(emptyList<String>(), 2)),
                result = JsonLogicResult.Success(-2)
            ),
            TestInput(
                expression = mapOf("-" to emptyList<Double>()),
                result = JsonLogicResult.NullResultFailure
            ),
            TestInput(expression = mapOf("-" to listOf("a", 2)), result = JsonLogicResult.NullResultFailure),
            TestInput(
                expression = mapOf("-" to listOf(listOf(2, "a"), 2)),
                result = JsonLogicResult.NullResultFailure
            ),
            TestInput(
                expression = mapOf("-" to listOf(listOf("a", 2), 2)),
                result = JsonLogicResult.NullResultFailure
            ),
            TestInput(
                expression = mapOf("-" to listOf(listOf(2, 2), 2)),
                result = JsonLogicResult.NullResultFailure
            ),
            TestInput(
                expression = mapOf("-" to listOf(listOf(2, "a"), listOf("a", 2))),
                result = JsonLogicResult.NullResultFailure
            ),
            TestInput(
                expression = mapOf("-" to listOf(listOf("5", listOf("5")), listOf("5"), listOf("5"))),
                result = JsonLogicResult.NullResultFailure
            ),
            TestInput(expression = mapOf("-" to listOf("banana")), result = JsonLogicResult.NullResultFailure),
            TestInput(
                expression = mapOf("-" to listOf(listOf(listOf("5"), listOf(6)))),
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
        expression = mapOf("-" to listOf("2.3", 3.2)),
        result = JsonLogicResult.Success(-0.9)
    ),
)
