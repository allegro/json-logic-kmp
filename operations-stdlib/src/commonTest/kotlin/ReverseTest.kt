
import JsonLogicResult.Failure
import JsonLogicResult.Success
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class ReverseTest : FunSpec({
    val operatorName = "reverse"
    val logicEngine = JsonLogicEngine.Builder()
        .addStandardOperation(operatorName, Reverse)
        .build()

    withData(
        nameFn = { input -> "Should evaluated ${input.expression} with given ${input.data} result in ${input.result}" },
        ts = listOf(
            TestInput(
                expression = mapOf(operatorName to "apple"),
                result = Success("elppa")
            ),
            TestInput(
                expression = mapOf(operatorName to listOf("apple")),
                result = Success("elppa")
            ),
            TestInput(
                expression = mapOf(operatorName to "12345"),
                result = Success("54321")
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(listOf(1, 2, 3, 4, 5))),
                result = Success(listOf(5,4,3,2,1))
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(listOf("element1", "element2", "element3"))),
                result = Success(listOf("element3", "element2", "element1"))
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(mapOf("var" to "key"))),
                data = mapOf("key" to listOf(1, 2, 3, 4, 5)),
                result = Success(listOf(5,4,3,2,1))
            ),
            TestInput(
                expression = mapOf(operatorName to mapOf("var" to "key")),
                data = mapOf("key" to "12345"),
                result = Success("54321")
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
