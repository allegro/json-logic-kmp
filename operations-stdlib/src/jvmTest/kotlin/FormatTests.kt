import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class FormatTests : FunSpec({
    val logicEngine = JsonLogicEngine.Builder()
        .addStandardOperation("format", Format)
        .build()

    withData(
        nameFn = { input -> "Should evaluated ${input.expression} with given ${input.data} result in ${input.result}" },
        ts = listOf(
            TestInput(
                expression = mapOf(
                    "format" to listOf(
                        "Kmp is %s love %d%, trust me %.2f",
                        listOf("my", 100, 3.14159)
                    )
                ),
                result = JsonLogicResult.Success("Kmp is my love 100%, trust me 3.14")
            ),
            TestInput(
                expression = mapOf(
                    "format" to listOf(
                        "Love kmp"
                    )
                ),
                result = JsonLogicResult.Success("Love kmp")
            ),
            TestInput(
                expression = mapOf(
                    "format" to ""
                ),
                result = JsonLogicResult.Success("")
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

