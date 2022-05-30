package array

import JsonLogicEngine
import JsonLogicResult.Success
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class SortTest : FunSpec({
    val operatorName = "sort"
    val logicEngine = JsonLogicEngine.Builder().addStandardOperation(operatorName, Sort).build()

    withData(
        nameFn = { input -> "Should evaluated ${input.expression} with given ${input.data} result in ${input.result}" },
        ts = listOf(
            TestInput(
                expression = mapOf(operatorName to listOf(listOf(1, 2, 3))),
                result = Success(listOf(1.0, 2.0, 3.0))
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(listOf(1, 2, 3), "asc")),
                result = Success(listOf(1.0, 2.0, 3.0))
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(listOf(1, 2, 3), "desc")),
                result = Success(listOf(3.0, 2.0, 1.0))
            ),
//            TestInput(
//                expression = mapOf(operatorName to "banana"),
//                result = JsonLogicResult.Failure.NullResult
//            ),
//            TestInput(
//                expression = mapOf(operatorName to null),
//                result = JsonLogicResult.Failure.NullResult
//            ),
//            TestInput(
//                expression = mapOf(operatorName to true),
//                result = JsonLogicResult.Failure.NullResult
//            ),
//            TestInput(
//                expression = mapOf(operatorName to emptyList<Any>()),
//                result = JsonLogicResult.Failure.NullResult
//            ),
//            TestInput(
//                expression = mapOf(operatorName to listOf(null, mapOf("var" to ""))),
//                result = JsonLogicResult.Failure.NullResult
//            ),
//            TestInput(
//                expression = mapOf(operatorName to listOf(emptyList<String>(), mapOf("var" to ""))),
//                result = JsonLogicResult.Failure.NullResult
//            ),
//            TestInput(
//                expression = mapOf(operatorName to listOf(listOf(false, false), mapOf("var" to ""))),
//                result = JsonLogicResult.Failure.NullResult
//            ),
//            TestInput(
//                expression = mapOf(operatorName to listOf(listOf(true, false), mapOf("var" to ""))),
//                result = JsonLogicResult.Success(true)
//            ),
//            TestInput(
//                expression = mapOf(operatorName to listOf(listOf(true, false))),
//                result = JsonLogicResult.Failure.NullResult
//            ),
//            TestInput(
//                expression = mapOf(
//                    operatorName to listOf(
//                        mapOf(
//                            "filter" to listOf(
//                                mapOf("var" to "integers"),
//                                mapOf("%" to listOf(mapOf("var" to ""), 2))
//                            )
//                        ), mapOf(">" to listOf(mapOf("var" to ""), 1))
//                    )
//                ),
//                data = mapOf("integers" to listOf(1, 2, 3, 4, 5)),
//                result = JsonLogicResult.Success(3)
//            ),
//            TestInput(
//                expression = mapOf(operatorName to listOf(listOf(-1, 1, 2, 3), mapOf(">" to listOf(mapOf("var" to ""), 0)))),
//                result = JsonLogicResult.Success(1)
//            ),
//            TestInput(
//                expression = mapOf(operatorName to listOf(listOf(0, 0, 0, 0), mapOf("==" to listOf(mapOf("var" to ""), 0)))),
//                result = JsonLogicResult.Success(0)
//            ),
//            TestInput(
//                expression = mapOf(operatorName to listOf(listOf(0, 0, 0, 0), mapOf("===" to listOf(mapOf("var" to ""), 0)))),
//                result = JsonLogicResult.Success(0)
//            ),
//            TestInput(
//                expression = mapOf(operatorName to listOf(listOf(0, 0, 0, 0), mapOf("!=" to listOf(mapOf("var" to ""), 0)))),
//                result = JsonLogicResult.Failure.NullResult
//            ),
//            TestInput(
//                expression = mapOf(
//                    operatorName to listOf(
//                        listOf(-1, "b", "a", 3),
//                        mapOf(">" to listOf(mapOf("var" to ""), 0))
//                    )
//                ),
//                result = JsonLogicResult.Success(3)
//            ),
//            TestInput(
//                expression = mapOf(operatorName to listOf(listOf(-1, 1, 2, 3), mapOf("<" to listOf(mapOf("var" to ""), 0)))),
//                result = JsonLogicResult.Success(-1)
//            ),
//            TestInput(
//                expression = mapOf(
//                    operatorName to listOf(
//                        listOf("banana", "apple"),
//                        mapOf("==" to listOf(mapOf("var" to ""), "apple"))
//                    )
//                ),
//                result = JsonLogicResult.Success("apple")
//            ),
//            TestInput(
//                expression = mapOf(
//                    operatorName to listOf(
//                        mapOf("var" to "fruits"),
//                        mapOf("==" to listOf(mapOf("var" to ""), "pineapple"))
//                    )
//                ),
//                data = mapOf("fruits" to listOf("apple", "banana", "pineapple")),
//                result = JsonLogicResult.Success("pineapple")
//            ),
//            TestInput(
//                expression = mapOf(
//                    operatorName to listOf(
//                        listOf("a", "b", "c"),
//                        mapOf("<" to listOf(mapOf("var" to ""), 0))
//                    )
//                ),
//                result = JsonLogicResult.Failure.NullResult
//            ),
        )
        // given
    ) { testInput: TestInput ->
        // when
        val evaluationResult = logicEngine.evaluate(testInput.expression, testInput.data)

        // then
        evaluationResult shouldBe testInput.result
    }
})
