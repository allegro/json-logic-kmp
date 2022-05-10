import JsonLogicResult.Failure
import JsonLogicResult.Success
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class FindTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().addFunctionalOperation("find", Find).build()
    
    withData(
        nameFn = { input -> "Should evaluated ${input.expression} with given ${input.data} result in ${input.result}" },
        ts = listOf(
            TestInput(
                expression = mapOf("find" to 1.3),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf("find" to "banana"),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf("find" to null),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf("find" to true),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf("find" to emptyList<Any>()),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf("find" to listOf(null, mapOf("var" to ""))),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf("find" to listOf(emptyList<String>(), mapOf("var" to ""))),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf("find" to listOf(listOf(false, false), mapOf("var" to ""))),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf("find" to listOf(listOf(true, false), mapOf("var" to ""))),
                result = Success(true)
            ),
            TestInput(
                expression = mapOf("find" to listOf(listOf(true, false))),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    "find" to listOf(
                        mapOf(
                            "filter" to listOf(
                                mapOf("var" to "integers"),
                                mapOf("%" to listOf(mapOf("var" to ""), 2))
                            )
                        ), mapOf(">" to listOf(mapOf("var" to ""), 1))
                    )
                ),
                data = mapOf("integers" to listOf(1, 2, 3, 4, 5)),
                result = Success(3)
            ),
            TestInput(
                expression = mapOf("find" to listOf(listOf(-1, 1, 2, 3), mapOf(">" to listOf(mapOf("var" to ""), 0)))),
                result = Success(1)
            ),
            TestInput(
                expression = mapOf("find" to listOf(listOf(0, 0, 0, 0), mapOf("==" to listOf(mapOf("var" to ""), 0)))),
                result = Success(0)
            ),
            TestInput(
                expression = mapOf("find" to listOf(listOf(0, 0, 0, 0), mapOf("===" to listOf(mapOf("var" to ""), 0)))),
                result = Success(0)
            ),
            TestInput(
                expression = mapOf("find" to listOf(listOf(0, 0, 0, 0), mapOf("!=" to listOf(mapOf("var" to ""), 0)))),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    "find" to listOf(
                        listOf(-1, "b", "a", 3),
                        mapOf(">" to listOf(mapOf("var" to ""), 0))
                    )
                ),
                result = Success(3)
            ),
            TestInput(
                expression = mapOf("find" to listOf(listOf(-1, 1, 2, 3), mapOf("<" to listOf(mapOf("var" to ""), 0)))),
                result = Success(-1)
            ),
            TestInput(
                expression = mapOf(
                    "find" to listOf(
                        listOf("banana", "apple"),
                        mapOf("==" to listOf(mapOf("var" to ""), "apple"))
                    )
                ),
                result = Success("apple")
            ),
            TestInput(
                expression = mapOf(
                    "find" to listOf(
                        mapOf("var" to "fruits"),
                        mapOf("==" to listOf(mapOf("var" to ""), "pineapple"))
                    )
                ),
                data = mapOf("fruits" to listOf("apple", "banana", "pineapple")),
                result = Success("pineapple")
            ),
            TestInput(
                expression = mapOf(
                    "find" to listOf(
                        listOf("a", "b", "c"),
                        mapOf("<" to listOf(mapOf("var" to ""), 0))
                    )
                ),
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
