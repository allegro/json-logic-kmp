import JsonLogicResult.Success
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class LengthTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().addFunctionalOperation("length", Length).build()

    withData(
        nameFn = { input -> "Should evaluated ${input.expression} with given ${input.data} result in ${input.result}" },
        ts = listOf(
            TestInput(
                expression = mapOf("length" to "test"),
                result = Success(4)
            ),
            TestInput(
                expression = mapOf(
                    "length" to listOf(
                        mapOf("var" to "fruits")
                    )
                ),
                data = mapOf("fruits" to listOf("apple")),
                result = Success(4)
            ),
            TestInput(
                expression = mapOf("length" to listOf("test", "test2")),
                result = JsonLogicResult.NullResultFailure
            ),
            TestInput(
                expression = mapOf(
                    "length" to listOf(
                        mapOf("var" to "fruits")
                    )
                ),
                data = mapOf("fruits" to listOf("apple", "banana", "pineapple")),
                result = JsonLogicResult.NullResultFailure
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
