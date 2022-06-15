
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import JsonLogicResult.Success
import JsonLogicResult.Failure
import string.Replace
import string.Trim

class ReplaceTest:FunSpec({
    val operatorName = "replace"
    val logicEngine = JsonLogicEngine.Builder()
        .addStandardOperations(
            mapOf(
                operatorName to Replace,
                "trim" to Trim
            )
        )
        .build()

    withData(
        nameFn = { input -> "Should evaluated ${input.expression} with given ${input.data} result in ${input.result}" },
        ts = listOf(

            TestInput(
                expression = mapOf(operatorName to listOf("crazy cat", "razy", "ute", "all")),
                result = Success("cute cat")
            ),
            TestInput(
                expression = mapOf(operatorName to listOf("crazy cat", "c", "C", "1")),
                result = Success("Crazy cat")
            ),
            TestInput(
                expression = mapOf(operatorName to listOf("crazy cat", "c", "C", "0")),
                result = Success("crazy cat")
            ),
            TestInput(
                expression = mapOf(operatorName to listOf("crazy cat", "c", "C", "2")),
                result = Success("Crazy Cat")
            ),
            TestInput(
                expression = mapOf(operatorName to listOf("crazy cat", "c", "C", "3")),
                result = Success("Crazy Cat")
            ),
            TestInput(
                expression = mapOf(operatorName to listOf("crazy cat cuts cotton clothes with success", "c", "C", "5")),
                result = Success("Crazy Cat Cuts Cotton Clothes with success")
            ),
            TestInput(
                expression = mapOf(operatorName to listOf("crazy cat", "cat", "hamster", "all")),
                result = Success("crazy hamster")
            ),
            TestInput(
                expression = mapOf(operatorName to listOf("crazy cat cat cat", "cat", "hamster", "all")),
                result = Success("crazy hamster hamster hamster")
            ),
            TestInput(
                expression = mapOf(operatorName to listOf("", "", "hamster", "all")),
                result = Success("hamster")
            ),
            TestInput(
                expression = mapOf(operatorName to listOf("crazy hamster", "cat", "dog", "all")),
                result = Success("crazy hamster")
            ),
            TestInput(
                expression = mapOf(operatorName to listOf("crazy hamster", "cat", "dog", "luke")),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(mapOf("var" to "key"), "_", "@", "all")
                ),
                data = mapOf("key" to "_cat_"),
                result = Success("@cat@")
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(mapOf("var" to "key"), "_", "@", "all")
                ),
                data = mapOf("key1" to "_cat_"),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(
                        mapOf(
                            "trim" to listOf("cute cat  ", " ", "end")
                        ),
                        "cat",
                        "dog",
                        "all"
                    )
                ),
                result = Success("cute dog")
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
            TestInput(
                expression = mapOf(operatorName to 1.3),
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
