package array

import JsonLogicEngine
import JsonLogicResult.Failure
import JsonLogicResult.Success
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class DistinctTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().addStandardOperation("distinct", Distinct).build()

    withData(
        nameFn = { input -> "Should evaluated ${input.expression} with given ${input.data} result in ${input.result}" },
        ts = listOf(
            TestInput(
                expression = mapOf("distinct" to listOf(listOf(listOf("apple"), listOf("apple"), listOf("pineapple")))),
                result = Success(listOf(listOf(listOf("apple"), listOf("apple"), listOf("pineapple"))))
            ),
            TestInput(
                expression = mapOf("distinct" to listOf(listOf("apple"), listOf("apple"), listOf("pineapple"))),
                result = Success(listOf(listOf("apple"), listOf("pineapple")))
            ),
            TestInput(
                expression = mapOf("distinct" to listOf(listOf(listOf(null), listOf(null), listOf(null)))),
                result = Success(listOf(listOf(listOf(null), listOf(null), listOf(null))))
            ),
            TestInput(
                expression = mapOf("distinct" to listOf(listOf(null), listOf(null), listOf(null))),
                result = Success(listOf(listOf(null)))
            ),
            TestInput(
                expression = mapOf("distinct" to 1.3),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf("distinct" to listOf("true", true, 0, 1)),
                result = Success(listOf("true", true, 0, 1))
            ),
            TestInput(
                expression = mapOf("distinct" to listOf(listOf("true", true, 0, 1))),
                result = Success(listOf(listOf("true", true, 0, 1)))
            ),
            TestInput(
                expression = mapOf("distinct" to listOf(listOf(null, null, null, null))),
                result = Success(listOf(listOf(null, null, null, null)))
            ),
            TestInput(
                expression = mapOf("distinct" to listOf(null, null, null, null)),
                result = Success(listOf(null))
            ),
            TestInput(
                expression = mapOf(
                    "distinct" to listOf(
                        listOf(
                            mapOf("var" to "fruit"),
                            mapOf("var" to "vegetable"),
                            mapOf("var" to "mushroom")
                        )
                    )
                ),
                data = mapOf("fruit" to null, "vegetable" to null, "mushroom" to true),
                result = Success(listOf(listOf(null, null, true)))
            ),
            TestInput(
                expression = mapOf(
                    "distinct" to listOf(
                        mapOf("var" to "fruit"),
                        mapOf("var" to "vegetable"),
                        mapOf("var" to "mushroom")
                    )
                ),
                data = mapOf("fruit" to null, "vegetable" to null, "mushroom" to true),
                result = Success(listOf(null, true))
            ),
            TestInput(
                expression = mapOf("distinct" to "banana"),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf("distinct" to emptyList<Any>()),
                result = Success(emptyList<Any>())
            ),
            TestInput(
                expression = mapOf("distinct" to null),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf("distinct" to true),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf("distinct" to listOf(emptyList<Any>())),
                result = Success(listOf(emptyList<Any>()))
            ),
            TestInput(
                expression = mapOf("distinct" to listOf(null, mapOf("var" to ""))),
                result = Success(listOf(null, emptyMap<String, Any>()))
            ),
            TestInput(
                expression = mapOf("distinct" to listOf(emptyList<String>(), mapOf("var" to ""))),
                result = Success(listOf(emptyList<String>(), emptyMap<String, Any>()))
            ),
            TestInput(
                expression = mapOf("distinct" to listOf(listOf(false, false), mapOf("var" to ""))),
                result = Success(listOf(listOf(false, false), emptyMap<String, Any>()))
            ),
            TestInput(
                expression = mapOf("distinct" to listOf(false, mapOf("var" to ""))),
                result = Success(listOf(false, emptyMap<String, Any>()))
            ),
            TestInput(
                expression = mapOf("distinct" to listOf(listOf(true, false), mapOf("var" to ""))),
                result = Success(listOf(listOf(true, false), emptyMap<String, Any>()))
            ),
            TestInput(
                expression = mapOf("distinct" to listOf(true, false, mapOf("var" to ""))),
                result = Success(listOf(true, false, emptyMap<String, Any>()))
            ),
            TestInput(
                expression = mapOf("distinct" to listOf(listOf(true, false))),
                result = Success(listOf(listOf(true, false)))
            ),
            TestInput(
                expression = mapOf("distinct" to listOf(true, false)),
                result = Success(listOf(true, false))
            ),
            TestInput(
                expression = mapOf(
                    "distinct" to listOf(
                        mapOf(
                            "filter" to listOf(
                                mapOf("var" to "integers"),
                                mapOf("%" to listOf(mapOf("var" to ""), 2))
                            )
                        ), mapOf(">" to listOf(mapOf("var" to ""), 1))
                    )
                ),
                data = mapOf("integers" to listOf(1, 2, 3, 4, 5)),
                result = Success(listOf(listOf(1, 3, 5), false))
            ),
            TestInput(
                expression = mapOf(
                    "distinct" to listOf(
                        listOf(-1, 1, 2, 3),
                        mapOf(">" to listOf(mapOf("var" to ""), 0))
                    )
                ),
                result = Success(listOf(listOf(-1, 1, 2, 3), false))
            ),
            TestInput(
                expression = mapOf("distinct" to listOf(listOf(7, 7, 7), mapOf("==" to listOf(mapOf("var" to ""), 0)))),
                result = Success(listOf(listOf(7, 7, 7), false))
            ),
            TestInput(
                expression = mapOf("distinct" to listOf(listOf(3, 3), mapOf("===" to listOf(mapOf("var" to ""), 0)))),
                result = Success(listOf(listOf(3, 3), false))
            ),
            TestInput(
                expression = mapOf("distinct" to listOf(listOf(0, 1, 0), mapOf("!=" to listOf(mapOf("var" to ""), 0)))),
                result = Success(listOf(listOf(0, 1, 0), true))
            ),
            TestInput(
                expression = mapOf(
                    "distinct" to listOf(
                        listOf(-1, "b", "a", true),
                        mapOf(">" to listOf(mapOf("var" to ""), 0))
                    )
                ),
                result = Success(listOf(listOf(-1, "b", "a", true), false))
            ),
            TestInput(
                expression = mapOf(
                    "distinct" to mapOf(
                        "merge" to listOf(
                            listOf(-1, "b", "a", true),
                            mapOf(">" to listOf(mapOf("var" to ""), 0))
                        )
                    )
                ),
                result = Success(listOf(-1, "b", "a", true, false))
            ),
            TestInput(
                expression = mapOf(
                    "distinct" to listOf(
                        listOf("strawberry", true, 2, 3),
                        mapOf("<" to listOf(mapOf("var" to ""), 0))
                    )
                ),
                result = Success(listOf(listOf("strawberry", true, 2, 3), false))
            ),
            TestInput(
                expression = mapOf(
                    "distinct" to mapOf(
                        "merge" to listOf(
                            listOf("strawberry", true, 2, 3),
                            mapOf("<" to listOf(mapOf("var" to ""), 0))
                        )
                    )
                ),
                result = Success(listOf("strawberry", true, 2, 3, false))
            ),
            TestInput(
                expression = mapOf(
                    "distinct" to listOf(
                        listOf("banana", "apple"),
                        mapOf("==" to listOf(mapOf("var" to ""), "apple"))
                    )
                ),
                result = Success(listOf(listOf("banana", "apple"), false))
            ),
            TestInput(
                expression = mapOf(
                    "distinct" to mapOf(
                        "merge" to listOf(
                            listOf("banana", "apple"),
                            mapOf("==" to listOf(mapOf("var" to ""), "apple"))
                        )
                    )
                ),
                result = Success(listOf("banana", "apple", false))
            ),
            TestInput(
                expression = mapOf(
                    "distinct" to listOf(
                        mapOf("var" to "fruits"),
                        mapOf("==" to listOf(mapOf("var" to ""), "pineapple"))
                    )
                ),
                data = mapOf("fruits" to listOf("apple", "banana", "pineapple")),
                result = Success(listOf(listOf("apple", "banana", "pineapple"), false))
            ),
            TestInput(
                expression = mapOf(
                    "distinct" to mapOf(
                        "merge" to listOf(
                            mapOf("var" to "fruits"),
                            mapOf("==" to listOf(mapOf("var" to ""), "pineapple"))
                        )
                    )
                ),
                data = mapOf("fruits" to listOf("apple", "banana", "pineapple")),
                result = Success(listOf("apple", "banana", "pineapple", false))
            ),
            TestInput(
                expression = mapOf(
                    "distinct" to listOf("a", 3, "c")
                ),
                result = Success(listOf("a", 3, "c"))
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

