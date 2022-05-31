package array

import JsonLogicEngine
import JsonLogicResult.Success
import JsonLogicResult.Failure
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
            // TODO add more cases with string list
            TestInput(
                expression = mapOf(operatorName to listOf(mapOf(
                    "map" to listOf(
                        mapOf("var" to "integers", "var" to "integers"),
                        mapOf("%" to listOf(mapOf("var" to ""), 3)),
                        mapOf("var" to "integers", "var" to "integers"),
                    )
                ), "desc")),
                data = mapOf("integers" to listOf(1, 2, 3, 4, 5)),
                result = Success(listOf(2.0, 2.0, 1.0, 1.0, 0.0))
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(mapOf(
                    "map" to listOf(
                        mapOf("var" to "integers"),
                        mapOf("%" to listOf(mapOf("var" to ""), 2))
                    )
                ), "asc")),
                data = mapOf("integers" to listOf(1, 2, 3, 4, 5)),
                result = Success(listOf(0.0, 0.0, 1.0, 1.0, 1.0))
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(listOf(1, 2, 3))),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(listOf(1.0, 2, 3.5), "desc")),
                result = Success(listOf(3.5, 2.0, 1.0))
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(0, "desc")),
                result = Success(listOf(0.0))
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(listOf(0.01, 0.01, 0.001), "desc")),
                result = Success(listOf(0.01, 0.01, 0.001))
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(listOf(1, "true", 3), "asc")),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(listOf(1, 3, null), "asc")),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(listOf(1, 2), listOf(3, 4), "asc")),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(listOf(1, "2", 3), "asc")),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(listOf(1, 2, 3), "asc")),
                result = Success(listOf(1.0, 2.0, 3.0))
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(listOf(1, 2, 3), "desc")),
                result = Success(listOf(3.0, 2.0, 1.0))
            ),
            TestInput(
                expression = mapOf(operatorName to "banana"),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(listOf("banana", "apple", "strawberry"), "asc")),
                result = Success(listOf("apple", "banana", "strawberry"))
            ),
            TestInput(
                expression = mapOf(operatorName to null),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to true),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to emptyList<Any>()),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(null, mapOf("var" to ""))),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(emptyList<String>(), mapOf("var" to ""))),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(listOf(false, false), mapOf("var" to ""))),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(listOf(true, false), "asc")),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(
                        mapOf("var" to "fruits"),
                        mapOf("==" to listOf(mapOf("var" to ""), "pineapple"))
                    )
                ),
                data = mapOf("fruits" to listOf("apple", "banana", "pineapple")),
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
