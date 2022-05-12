package string

import JsonLogicResult.Failure
import JsonLogicResult.Success
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class UppercaseTest : FunSpec({
    val operatorName = "uppercase"
    val logicEngine = JsonLogicEngine.Builder().addStandardOperation(operatorName, Uppercase).build()

    withData(
        nameFn = { input -> "Should evaluated ${input.expression} with given ${input.data} result in ${input.result}" },
        ts = listOf(
            TestInput(
                expression = mapOf(operatorName to "banana"),
                result = Success("BANANA")
            ),
            TestInput(
                expression = mapOf(operatorName to ""),
                result = Success("")
            ),
            TestInput(
                expression = mapOf(operatorName to " "),
                result = Success(" ")
            ),
            TestInput(
                expression = mapOf(operatorName to "123"),
                result = Success("123")
            ),
            TestInput(
                expression = mapOf(operatorName to "Test"),
                result = Success("TEST")
            ),
            TestInput(
                expression = mapOf(operatorName to "TEST ME!"),
                result = Success("TEST ME!")
            ),
            TestInput(
                expression = mapOf(operatorName to mapOf("var" to "key")),
                data = mapOf("key" to "apple"),
                result = Success("APPLE")
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
