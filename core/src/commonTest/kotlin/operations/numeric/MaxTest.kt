package operations.numeric

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import valueShouldBe

class MaxTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    withData(
        nameFn = { input -> "Should evaluated ${input.expression} with given ${input.data} result in ${input.result}" },
        ts = listOf(
            TestInput(
                expression = mapOf("max" to listOf(1, 2, 3)),
                result = JsonLogicResult.Success(3)
            ),
            TestInput(
                expression = mapOf("max" to listOf(1, 3, 3)),
                result = JsonLogicResult.Success(3)
            ),
            TestInput(
                expression = mapOf("max" to listOf("-1", -2, "-3")),
                result = JsonLogicResult.Success(-1)
            ),
            TestInput(
                expression = mapOf("max" to listOf(3, 2, 1)),
                result = JsonLogicResult.Success(3)
            ),
            TestInput(
                expression = mapOf("max" to listOf(1)),
                result = JsonLogicResult.Success(1)
            ),
            TestInput(
                expression = mapOf("max" to listOf(1, "banana")),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf("max" to listOf(1, "banana", listOf(1, 2))),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf("max" to listOf(1, "2")),
                result = JsonLogicResult.Success(2)
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
