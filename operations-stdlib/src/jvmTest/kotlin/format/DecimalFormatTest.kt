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
                        "%.4f-%f-%.10f",
                        mapOf(
                            "map" to listOf(
                                mapOf("var" to "integers"),
                                mapOf("*" to listOf(mapOf("var" to ""), 0.1997))
                            )
                        )
                    )
                ),
                data = mapOf("integers" to listOf(12.31421554323, 2.245123555332, 3.8483)),
                result = JsonLogicResult.Success("2.4591-0.448351-0.7685055100")
            ),
            TestInput(
                expression = mapOf(
                    "decimalFormat" to listOf(
                        "%.1f", listOf(
                            mapOf("%" to listOf(3.5, 1.3))
                        )
                    )
                ),
                result = JsonLogicResult.Success("0.9")
            ),
            TestInput(
                expression = mapOf(
                    "decimalFormat" to listOf(
                        "%.2f", listOf(
                            mapOf("*" to listOf("1.3", "3.7"))
                        )
                    )
                ),
                result = JsonLogicResult.Success("4.81")
            ),
            TestInput(
                expression = mapOf(
                    "decimalFormat" to listOf(
                        "%.1f", listOf(
                            mapOf("-" to listOf("2.3", 3.2))
                        )
                    )
                ),
                result = JsonLogicResult.Success("-0.9")
            ),
            TestInput(
                expression = mapOf(
                    "decimalFormat" to listOf(
                        "%.0f.%2f",
                        listOf(
                            mapOf(
                                "/" to listOf(
                                    mapOf("var" to "number1"),
                                    mapOf("var" to "number2")
                                )
                            ),
                            mapOf(
                                "*" to listOf(
                                    mapOf("var" to "number1"),
                                    mapOf("var" to "number2")
                                )
                            )
                        )
                    )
                ),
                data = mapOf("number1" to 3.8569435, "number2" to 12.324),
                result = JsonLogicResult.Success("0.47.532972")
            ),
            TestInput(
                expression = mapOf(
                    "decimalFormat" to listOf(
                        "%f/%f",
                        listOf(
                            mapOf(
                                "+" to listOf(
                                    mapOf("var" to "number1"),
                                    mapOf("var" to "number2")
                                )
                            ),
                            mapOf(
                                "*" to listOf(
                                    mapOf("var" to "number1"),
                                    mapOf("var" to "number2")
                                )
                            )
                        )
                    )
                ),
                data = mapOf("number1" to 3.8569435, "number2" to 12.324),
                result = JsonLogicResult.Success("16.180943/47.532972")
            ),
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
                    "decimalFormat" to listOf("%a", 1.323)
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    "decimalFormat" to listOf("%b", "1")
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    "decimalFormat" to listOf("%c", 'A')
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    "decimalFormat" to listOf("%e", "1.23400093234")
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    "decimalFormat" to listOf("%g", "1332.23400093234")
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    "decimalFormat" to listOf("%h", "Hello World!")
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    "decimalFormat" to listOf("%o", 12)
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    "decimalFormat" to listOf("%x", 12)
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
                    "decimalFormat" to listOf("%.2f", listOf(null))
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

