package format

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class DecimalFormatTest : FunSpec({
    val operatorName = "decimalFormat"
    val logicEngine = JsonLogicEngine.Builder()
        .addStandardOperation(operatorName, DecimalFormat)
        .build()

    withData(
        nameFn = { input -> "Should evaluate decimal format operation into ${input.result}" },
        ts = listOf(
            TestInput(
                expression = mapOf(
                    operatorName to listOf(
                        "%10.9f",
                        "3455353.500002345"
                    )
                ),
                result = JsonLogicResult.Success("3455353.500002345")
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(
                        "%1.0f",
                        "3455353.500002345"
                    )
                ),
                result = JsonLogicResult.Success("3455354")
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(
                        "%1.9f",
                        "3455353.500002345"
                    )
                ),
                result = JsonLogicResult.Success("3455353.500002345")
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(
                        "%20.9f",
                        "3455353.500002345"
                    )
                ),
                result = JsonLogicResult.Success("   3455353.500002345")
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(
                        "%.4f",
                        "3.5"
                    )
                ),
                result = JsonLogicResult.Success("3.5000")
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(
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
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(
                        "%.1f",
                        mapOf("%" to listOf(3.5, 1.3))
                    )
                ),
                result = JsonLogicResult.Success("0.9")
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(
                        "%.2f",
                        mapOf("*" to listOf("1.3", "3.7"))
                    )
                ),
                result = JsonLogicResult.Success("4.81")
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(
                        "%.1f",
                        mapOf("-" to listOf("2.3", 3.2))
                    )
                ),
                result = JsonLogicResult.Success("-0.9")
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(
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
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(
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
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(
                        "Kmp is %s love %d, trust me %.2f %s",
                        listOf("my", 100, 3.14159, true)
                    )
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(
                        "%.0f",
                        "3"
                    )
                ),
                result = JsonLogicResult.Success("3")
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(
                        "%.0f",
                        3
                    )
                ),
                result = JsonLogicResult.Success("3")
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(
                        "%.0f",
                        3.00001
                    )
                ),
                result = JsonLogicResult.Success("3")
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(
                        "%.2f",
                        3.14001
                    )
                ),
                result = JsonLogicResult.Success("3.14")
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(
                        "%.2f",
                        "3.14001"
                    )
                ),
                result = JsonLogicResult.Success("3.14")
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(
                        "%f",
                        3.14001
                    )
                ),
                result = JsonLogicResult.Success("3.140010")
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(
                        "|%10d|",
                        listOf(101)
                    )
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(
                        "|%-10d|",
                        listOf(101)
                    )
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(
                        "%h",
                        listOf(101)
                    )
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(
                        "Love kmp"
                    )
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to ""
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to emptyList<Any>()
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(listOf(100), listOf(100))
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("100%", 200)
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("%", 100)
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("%", listOf(100))
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(null, listOf(100))
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(null, listOf(null))
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("%d", null)
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("%a", 1.323)
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("%b", "1")
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("%c", 'A')
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("%e", "1.23400093234")
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("%g", "1332.23400093234")
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("%h", "Hello World!")
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("%o", 12)
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("%x", 12)
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("%d", listOf(null))
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("%f", listOf(null))
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("%.2f", listOf(null))
                ),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("%d%.2f%s%d%.1f%s", listOf(null, 20.0, null, 30, 3.45, "kmp"))
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

