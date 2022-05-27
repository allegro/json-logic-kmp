package array

import JsonLogicEngine
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import JsonLogicResult.Success
import JsonLogicResult.Failure

class DistinctTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().addStandardOperation("distinct", Distinct).build()

    withData(
        nameFn = { input -> "Should evaluated ${input.expression} with given ${input.data} result in ${input.result}" },
        ts = listOf(
            TestInput(
                expression = mapOf("distinct" to listOf(listOf(listOf("apple"), listOf("apple"), listOf("pineapple")))),
                result = Success(listOf(listOf("apple"), listOf("pineapple")))
            ),
            TestInput(
                expression = mapOf("distinct" to listOf(listOf(listOf(null), listOf(null), listOf(null)))),
                result = Success(listOf(listOf(null)))
            ),
            TestInput(
                expression = mapOf("distinct" to 1.3),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf("distinct" to listOf("true", true, 0, 1)),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf("distinct" to listOf(listOf("true", true, 0, 1))),
                result = Success(listOf("true", true, 0, 1))
            ),
            TestInput(
                expression = mapOf("distinct" to listOf(listOf(null, null, null, null))),
                result = Success(listOf(null))
            ),
            TestInput(
                expression = mapOf("distinct" to listOf(listOf(mapOf("var" to "fruit"), mapOf("var" to "vegetable"), mapOf("var" to "mushroom")))),
                data = mapOf("fruit" to null, "vegetable" to null, "mushroom" to true),
                result = Success(listOf(null, true))
            ),
            TestInput(
                expression = mapOf("distinct" to "banana"),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf("distinct" to emptyList<Any>()),
                result = Failure.NullResult
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
                expression = mapOf("distinct" to emptyList<Any>()),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf("distinct" to listOf(null, mapOf("var" to ""))),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf("distinct" to listOf(emptyList<String>(), mapOf("var" to ""))),
                result = Success(emptyList<String>())
            ),
            TestInput(
                expression = mapOf("distinct" to listOf(listOf(false, false), mapOf("var" to ""))),
                result = Success(listOf(false))
            ),
            TestInput(
                expression = mapOf("distinct" to listOf(listOf(true, false), mapOf("var" to ""))),
                result = Success(listOf(true, false))
            ),
            TestInput(
                expression = mapOf("distinct" to listOf(listOf(true, false))),
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
                result = Success(listOf(1, 3, 5))
            ),
            TestInput(
                expression = mapOf("distinct" to listOf(listOf(-1, 1, 2, 3), mapOf(">" to listOf(mapOf("var" to ""), 0)))),
                result = Success(listOf(-1, 1, 2, 3))
            ),
            TestInput(
                expression = mapOf("distinct" to listOf(listOf(7, 7, 7), mapOf("==" to listOf(mapOf("var" to ""), 0)))),
                result = Success(listOf(7))
            ),
            TestInput(
                expression = mapOf("distinct" to listOf(listOf(3, 3), mapOf("===" to listOf(mapOf("var" to ""), 0)))),
                result = Success(listOf(3))
            ),
            TestInput(
                expression = mapOf("distinct" to listOf(listOf(0, 1, 0), mapOf("!=" to listOf(mapOf("var" to ""), 0)))),
                result = Success(listOf(0, 1))
            ),
            TestInput(
                expression = mapOf(
                    "distinct" to listOf(
                        listOf(-1, "b", "a", true),
                        mapOf(">" to listOf(mapOf("var" to ""), 0))
                    )
                ),
                result = Success(listOf(-1, "b", "a", true))
            ),
            TestInput(
                expression = mapOf("distinct" to listOf(listOf("strawberry", true, 2, 3), mapOf("<" to listOf(mapOf("var" to ""), 0)))),
                result = Success(listOf("strawberry", true, 2, 3))
            ),
            TestInput(
                expression = mapOf(
                    "distinct" to listOf(
                        listOf("banana", "apple"),
                        mapOf("==" to listOf(mapOf("var" to ""), "apple"))
                    )
                ),
                result = Success(listOf("banana", "apple"))
            ),
            TestInput(
                expression = mapOf(
                    "distinct" to listOf(
                        mapOf("var" to "fruits"),
                        mapOf("==" to listOf(mapOf("var" to ""), "pineapple"))
                    )
                ),
                data = mapOf("fruits" to listOf("apple", "banana", "pineapple")),
                result = Success(listOf("apple", "banana", "pineapple"))
            ),
            TestInput(
                expression = mapOf(
                    "distinct" to listOf(listOf("a", 3, "c"))
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

