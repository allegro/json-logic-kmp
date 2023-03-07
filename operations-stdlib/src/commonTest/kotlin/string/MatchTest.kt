import JsonLogicResult.Failure
import JsonLogicResult.Success
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import string.Match

class MatchTest : FunSpec({
    val operatorName = "match"
    val logicEngine = JsonLogicEngine.Builder()
        .addStandardOperations(
            mapOf(
                operatorName to Match
            )
        ).build()
    withData(
        nameFn = { input -> "Should evaluated ${input.expression} with given ${input.data} result in ${input.result}" },
        ts = listOf(
            TestInput(
                expression = mapOf(
                    operatorName to listOf("123", "^[0-9]+$", emptyList<Any>())
                ),
                result = Success(true)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("123w", "^[0-9]+$", emptyList<Any>())
                ),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(".123", "^[0-9]+$", emptyList<Any>())
                ),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("A123", "^[0-9]+$", emptyList<Any>())
                ),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("ABC", "^[a-zA-Z0-9]+$", emptyList<Any>())
                ),
                result = Success(true)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("Abc", "^[a-zA-Z0-9]+$", emptyList<Any>())
                ),
                result = Success(true)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("AbcX", "^[a-zA-Z0-9]+$", emptyList<Any>())
                ),
                result = Success(true)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("123", "^[a-zA-Z0-9]+$", emptyList<Any>())
                ),
                result = Success(true)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("Abc123", "^[a-zA-Z0-9]+$", emptyList<Any>())
                ),
                result = Success(true)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("Abc123XYZ", "^[a-zA-Z0-9]+$", emptyList<Any>())
                ),
                result = Success(true)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("Abc123XYZ;", "^[a-zA-Z0-9]+$", emptyList<Any>())
                ),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("Abc123XYZ!", "^[a-zA-Z0-9]+$", emptyList<Any>())
                ),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to 123
                ),
                result = Failure.NullResult
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
                expression = mapOf(operatorName to listOf("1234")),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("Poland", "poland$", emptyList<Any>())
                ),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("Poland", "poland$", listOf("IGNORE_CASE"))
                ),
                result = Success(true)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("abc\nxyz\ndef", "^[a-z]*$", emptyList<Any>())
                ),
                result = Success(false)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("abc\nxyz\ndef", "^[a-z]*$", listOf("MULTILINE"))
                ),
                result = Success(true)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("Abc\nXYZ\ndEf", "^[a-z]*$", listOf("MULTILINE","IGNORE_CASE"))
                ),
                result = Success(true)
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
