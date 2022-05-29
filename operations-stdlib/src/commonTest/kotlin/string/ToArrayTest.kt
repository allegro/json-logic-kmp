package string

import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import JsonLogicResult.Success
import JsonLogicResult.Failure

class ToArrayTest : FunSpec({
    val operatorName = "toArray"
    val logicEngine = JsonLogicEngine.Builder().addStandardOperation(operatorName, ToArray).build()

    withData(
        nameFn = { input -> "Should evaluated ${input.expression} with given ${input.data} result in ${input.result}" },
        ts = listOf(
            TestInput(
                expression = mapOf(operatorName to "banana"),
                result = Success(listOf("b", "a", "n", "a", "n", "a"))
            ),
            TestInput(
                expression = mapOf(operatorName to listOf("banana")),
                result = Success(listOf("b", "a", "n", "a", "n", "a"))
            ),
            TestInput(
                expression = mapOf(operatorName to ""),
                result = Success(emptyList<String>())
            ),
            TestInput(
                expression = mapOf(operatorName to listOf("")),
                result = Success(emptyList<String>())
            ),
            TestInput(
                expression = mapOf(operatorName to " "),
                result = Success(listOf(" "))
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(" ")),
                result = Success(listOf(" "))
            ),
            TestInput(
                expression = mapOf(operatorName to "123"),
                result = Success(listOf("1", "2", "3"))
            ),
            TestInput(
                expression = mapOf(operatorName to 123),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to mapOf("var" to "key")),
                data = mapOf("key" to "APPLE"),
                result = Success(listOf("A", "P", "P", "L", "E"))
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
                expression = mapOf(operatorName to listOf("0.00001")),
                result = Success(listOf("0", ".", "0", "0", "0", "0", "1"))
            ),
            TestInput(
                expression = mapOf(operatorName to null),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(null)),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to true),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(false)),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to emptyList<Any>()),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(emptyList<Any>())),
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
