import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class FormatTests : FunSpec({
    val logicEngine = JsonLogicEngine.Builder()
        .addStandardOperation("format", Format)
        .build()

    withData(
        nameFn = { input -> "Should ev. ${input.expression} into ${input.result}" },
        ts = listOf(
            TestInput(
                expression = mapOf(
                    "format" to listOf(
                        "Kmp is %s love %d, trust me %.2f %s",
                        listOf("my", 100, 3.14159, true)
                    )
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    "format" to listOf(
                        "%.0f",
                        listOf(3)
                    // ios treats 3 as 0 in %.0f case
                    )
                ),
                result = JsonLogicResult.Success("3")
            ),
            TestInput(
                expression = mapOf(
                    "format" to listOf(
                        "|%10d|",
                        listOf(101)
                    )
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    "format" to listOf(
                        "|%-10d|",
                        listOf(101)
                    )
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    "format" to listOf(
                        "%h",
                        listOf(101)
                    )
                ),
                result = JsonLogicResult.Failure.NullResult
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
            TestInput(
                expression = mapOf(
                    "format" to emptyList<Any>()
                ),
                result = JsonLogicResult.Success("null")
            ),
            TestInput(
                expression = mapOf(
                    "format" to listOf(listOf(100), listOf(100))
                ),
                result = JsonLogicResult.Success("[100]")
            ),
            // % sign in format without format specifier
            TestInput(
                expression = mapOf(
                    "format" to listOf("100%", 200)
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    "format" to listOf("%", 100)
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    "format" to listOf("%", listOf(100))
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            // null checks
            TestInput(
                expression = mapOf(
                    "format" to listOf(null, listOf(100))
                ),
                result = JsonLogicResult.Success("null")
            ),
            TestInput(
                expression = mapOf(
                    "format" to listOf(null, listOf(null))
                ),
                result = JsonLogicResult.Success("null")
            ),
            TestInput(
                expression = mapOf(
                    "format" to listOf("%d", null)
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    "format" to listOf("%d", listOf(null))
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    "format" to listOf("%f", listOf(null))
                ),
                result = JsonLogicResult.Success("null")
            ),
            TestInput(
                expression = mapOf(
                    "format" to listOf("%d%.2f%s%d%.1f%s", listOf(null, 20.0, null, 30, 3.45, "kmp"))
                ),
                result = JsonLogicResult.Failure.NullResult
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

