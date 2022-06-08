import JsonLogicResult.Failure
import JsonLogicResult.Success
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import string.Trim

class TrimTest : FunSpec({
    val operatorName = "trim"
    val logicEngine = JsonLogicEngine.Builder()
        .addStandardOperations(
            mapOf(
                operatorName to Trim
            )
        ).build()

    withData(
        nameFn = { input -> "Should evaluated ${input.expression} with given ${input.data} result in ${input.result}" },
        ts = listOf(
            TestInput(
                expression = mapOf(operatorName to " some A "),
                result = Success("some A")
            ),
            TestInput(
                expression = mapOf(operatorName to " some A"),
                result = Success("some A")
            ),
            TestInput(
                expression = mapOf(operatorName to "some A"),
                result = Success("some A")
            ),
            TestInput(
                expression = mapOf(operatorName to "some A"),
                result = Success("some A")
            ),
            TestInput(
                expression = mapOf(operatorName to "     some A   "),
                result = Success("some A")
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(listOf("_cat_", "_", "start"))
                ),
                result = Success("cat_")
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(listOf("_cat_", "_", "end"))
                ),
                result = Success("_cat")
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(listOf("_cat_", "_", "bothEnds"))
                ),
                result = Success("cat")
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(listOf(" cat ", " ", "bothEnds"))
                ),
                result = Success("cat")
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(listOf("_cat_", "_"))
                ),
                result = Success("cat")
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(listOf("_cat_"))
                ),
                result = Success("_cat_")
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(listOf(" cat "))
                ),
                result = Success("cat")
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(listOf("cat "))
                ),
                result = Success("cat")
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(listOf(" cat"))
                ),
                result = Success("cat")
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(listOf("    cat       "))
                ),
                result = Success("cat")
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(listOf("_cat_", "______"))
                ),
                result = Failure.MissingOperation
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(listOf("_cat_", ""))
                ),
                result = Failure.MissingOperation
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
        )
        // given
    ) { testInput: TestInput ->
        // when
        val evaluationResult = logicEngine.evaluate(testInput.expression, testInput.data)

        // then
        evaluationResult shouldBe testInput.result
    }
})
