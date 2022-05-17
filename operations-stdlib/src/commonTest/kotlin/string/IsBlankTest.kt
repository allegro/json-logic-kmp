package string

import JsonLogicEngine
import JsonLogicResult.Failure
import JsonLogicResult.Success
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class IsBlankTest : FunSpec({
    val operatorName = "isBlank"
    val logicEngine = JsonLogicEngine.Builder()
        .addStandardOperation(operatorName, IsBlank)
        .build()

    withData(
        nameFn = { input -> "Should evaluated ${input.expression} with given ${input.data} result in ${input.result}" },
        ts = listOf(
            TestInput(
                expression = mapOf(operatorName to "apple"),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf(operatorName to "12345"),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf(operatorName to ""),
                result = Success(true)
            ),
            TestInput(
                expression = mapOf(operatorName to "      "),
                result = Success(true)
            ),
            TestInput(
                expression = mapOf(operatorName to "      apple"),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf(operatorName to "apple        "),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf(operatorName to mapOf("var" to "key")),
                data = mapOf("key" to "APPLE"),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf(operatorName to mapOf("var" to "key")),
                data = mapOf("key" to ""),
                result = Success(true)
            ),
            TestInput(
                expression = mapOf(operatorName to mapOf("var" to "key")),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to 1.3),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to null),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to true),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to emptyList<Any>()),
                result = Failure.NullResult
            ),
        )
        // given
    ) { testInput: TestInput ->
        // when
        val evaluationResult = logicEngine.evaluate(testInput.expression, testInput.data)

        // then
        evaluationResult shouldBe testInput.result
    }
})
