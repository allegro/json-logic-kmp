package format

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class DecimalFormatTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder()
        .addStandardOperation("decimalFormat", DecimalFormat)
        .build()

    withData(
        nameFn = { input -> "Should ev. ${input.expression} into ${input.result}" },
        ts = listOf(
            TestInput(
                expression = mapOf(
                    "decimalFormat" to listOf(
                        "Kmp is %s love %d, trust me %.2f %s",
                        listOf("my", 100, 3.14159, true)
                    )
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    "decimalFormat" to listOf(
                        "%.0f",
                        listOf(3)
                    )
                ),
                result = JsonLogicResult.Success("3")
            ),
            TestInput(
                expression = mapOf(
                    "decimalFormat" to listOf(
                        "%.0f",
                        listOf(3.00001)
                    )
                ),
                result = JsonLogicResult.Success("3")
            ),
            TestInput(
                expression = mapOf(
                    "decimalFormat" to listOf(
                        "%.2f",
                        listOf(3.14001)
                    )
                ),
                result = JsonLogicResult.Success("3.14")
            ),
            TestInput(
                expression = mapOf(
                    "decimalFormat" to listOf(
                        "%f",
                        listOf(3.14001)
                    )
                ),
                result = JsonLogicResult.Success("3.140010")
            ),
            TestInput(
                expression = mapOf(
                    "decimalFormat" to listOf(
                        "|%10d|",
                        listOf(101)
                    )
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    "decimalFormat" to listOf(
                        "|%-10d|",
                        listOf(101)
                    )
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    "decimalFormat" to listOf(
                        "%h",
                        listOf(101)
                    )
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    "decimalFormat" to listOf(
                        "Love kmp"
                    )
                ),
                result = JsonLogicResult.Success("Love kmp")
            ),
            TestInput(
                expression = mapOf(
                    "decimalFormat" to ""
                ),
                result = JsonLogicResult.Success("")
            ),
            TestInput(
                expression = mapOf(
                    "decimalFormat" to emptyList<Any>()
                ),
                result = JsonLogicResult.Success("null")
            ),
            TestInput(
                expression = mapOf(
                    "decimalFormat" to listOf(listOf(100), listOf(100))
                ),
                result = JsonLogicResult.Success("[100]")
            ),
            // % sign in decimalFormat without format specifier
            TestInput(
                expression = mapOf(
                    "decimalFormat" to listOf("100%", 200)
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    "decimalFormat" to listOf("%", 100)
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    "decimalFormat" to listOf("%", listOf(100))
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            // null checks
            TestInput(
                expression = mapOf(
                    "decimalFormat" to listOf(null, listOf(100))
                ),
                result = JsonLogicResult.Success("null")
            ),
            TestInput(
                expression = mapOf(
                    "decimalFormat" to listOf(null, listOf(null))
                ),
                result = JsonLogicResult.Success("null")
            ),
            TestInput(
                expression = mapOf(
                    "decimalFormat" to listOf("%d", null)
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    "decimalFormat" to listOf("%d", listOf(null))
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    "decimalFormat" to listOf("%f", listOf(null))
                ),
                result = JsonLogicResult.Success("null")
            ),
            TestInput(
                expression = mapOf(
                    "decimalFormat" to listOf("%d%.2f%s%d%.1f%s", listOf(null, 20.0, null, 30, 3.45, "kmp"))
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

