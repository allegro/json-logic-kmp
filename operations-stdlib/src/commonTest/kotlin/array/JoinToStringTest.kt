package array

import JsonLogicEngine
import JsonLogicResult.Success
import JsonLogicResult.Failure
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class JoinToStringTest : FunSpec({
    val operationName = "joinToString"
    val logicEngine =
        JsonLogicEngine.Builder().addStandardOperations(
            mapOf(
                operationName to JoinToString,
                "distinct" to Distinct
            )
        ).build()

    withData(
        nameFn = { input -> "Should evaluated $operationName with given ${input.data} result in ${input.result}" },
        ts = listOf(
            TestInput(
                expression = mapOf(
                    operationName to listOf(
                        mapOf(
                            "distinct" to listOf(
                                listOf(
                                    "strawberry",
                                    "apple",
                                    "banana",
                                    "banana",
                                    "pineapple"
                                )
                            )
                        ), " + ", "add some: ", " and mix!", 3, "random fruits"
                    )
                ),
                result = Success("add some: strawberry + apple + banana + random fruits and mix!")
            ),
            TestInput(
                expression = mapOf(
                    operationName to listOf(
                        mapOf(
                            "distinct" to listOf(
                                listOf(
                                    "strawberry",
                                    "apple",
                                    "banana",
                                    "banana",
                                    "pineapple"
                                )
                            )
                        ), " + ", "add some: ", " and mix!", null, "random fruits"
                    )
                ),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operationName to listOf(
                        mapOf("distinct" to listOf(mapOf("var" to "fruits"))), " + ", "add some: ", " and mix!", 3, "random fruits"
                    )
                ),
                data = mapOf(
                    "fruits" to listOf(
                        "strawberry",
                        "apple",
                        "banana",
                        "banana",
                        "pineapple"
                    )
                ),
                result = Success("add some: strawberry + apple + banana + random fruits and mix!")
            ),
            TestInput(
                expression = mapOf(operationName to listOf(listOf("test", "test", 1), ",", "", "", 30, "...")),
                result = Success("test,test,1")
            ),
            TestInput(
                expression = mapOf(operationName to listOf(listOf("test", "test", 1), ",", "", "", 30, "...")),
                result = Success("test,test,1")
            ),
            TestInput(
                expression = mapOf(
                    operationName to listOf(
                        listOf(listOf("test"), listOf("test"), 1),
                        ",",
                        "",
                        "",
                        30,
                        "..."
                    )
                ),
                result = Success("[test],[test],1")
            ),
            TestInput(
                expression = mapOf(operationName to listOf(listOf(null, true, 1), ",", "", "", 30, "...")),
                result = Success("null,true,1")
            ),
            TestInput(
                expression = mapOf(
                    operationName to listOf(
                        listOf("13", ".", 11, ".", 1990),
                        "",
                        "date:",
                        "",
                        4,
                        "19**"
                    )
                ),
                result = Success("date:13.11.19**")
            ),
            TestInput(
                expression = mapOf(operationName to listOf(listOf("sp", "ace"), "   ", "  ", " ", 30, "...")),
                result = Success("  sp   ace ")
            ),
            TestInput(
                expression = mapOf(operationName to listOf(listOf("sp", "ace"), "   ", "  ", " ", 30, "...")),
                result = Success("  sp   ace ")
            ),
            TestInput(
                expression = mapOf(operationName to listOf(listOf("fruit", "salad"), " ", "", "", 1, "*****")),
                result = Success("fruit *****")
            ),
            TestInput(
                expression = mapOf(operationName to listOf(listOf("fruit", "salad"), " ", "", "", 1.0, "*****")),
                result = Success("fruit *****")
            ),
            TestInput(
                expression = mapOf(operationName to listOf(listOf("fruit", "salad"), " ", "", "", 1.2, "*****")),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operationName to listOf(emptyList<String>(), " ", "", "", 1, "*****")),
                result = Success("")
            ),
            TestInput(
                expression = mapOf(operationName to listOf(emptyList<String>(), " ", "", "", 0, "*****")),
                result = Success("")
            ),
            TestInput(
                expression = mapOf(operationName to listOf(emptyList<String>(), " ", "no elements ", "to join", 0, "*****")),
                result = Success("no elements to join")
            ),
            TestInput(
                expression = mapOf(operationName to listOf(listOf(0.00, 0, 0), " ", "", "", 0, "*****")),
                result = Success("*****")
            ),
            TestInput(
                expression = mapOf(operationName to listOf(listOf(0.00000, 0.000000, 0.00000000), ".", "", "", 3, "*****")),
                result = Success("0.0.0.0.0.0")
            ),
            TestInput(
                expression = mapOf(operationName to listOf(listOf(127, 0, 0, 1), ".", "", "", 4, "*****")),
                result = Success("127.0.0.1")
            ),
            TestInput(
                expression = mapOf(operationName to listOf(listOf("test", "test", 1), ",", "", "", "30", "...")),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operationName to null),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operationName to true),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operationName to false),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operationName to 1.3),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operationName to "strawberry"),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operationName to mapOf("var" to "fruit")),
                data = mapOf("fruit" to "banana"),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operationName to emptyList<String>()),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operationName to listOf(listOf("test", "test", 1))),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operationName to listOf(listOf("test", "test", 1), ",", "", "", 30)),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operationName to listOf(listOf("test", "test", 1), ",", "", null, null, null)),
                result = Failure.NullResult
            )
        )
        // given
    ) { testInput: TestInput ->
        // when
        val evaluationResult = logicEngine.evaluate(testInput.expression, testInput.data)

        // then
        evaluationResult shouldBe testInput.result
    }
})
