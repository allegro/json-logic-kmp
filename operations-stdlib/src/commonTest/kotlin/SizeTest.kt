import JsonLogicResult.Failure
import JsonLogicResult.Success
import io.kotest.core.spec.style.FunSpec
import io.kotest.core.test.TestResult
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class SizeTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().addStandardOperation("size", Size).build()

    withData(
        nameFn = { input -> "Should evaluated ${input.expression} with given ${input.data} result in ${input.result}" },
        ts = listOf(
            TestInput(
                expression = mapOf("size" to listOf(listOf("test"))),
                result = Success(1)
            ),
            TestInput(
                expression = mapOf("size" to listOf(listOf("test", "test2"), "a")),
                result = Success(2)
            ),
            TestInput(
                expression = mapOf(
                    "size" to listOf(
                        mapOf("var" to "fruits"),
                        mapOf("var" to "fruits2")
                    )
                ),
                data = mapOf("fruits" to listOf(listOf("apple"), "banana", "pineapple"), "fruits2" to "testFruit"),
                result = Success(3)
            ),
            TestInput(
                expression = mapOf(
                    "size" to mapOf("var" to "fruits")
                ),
                data = mapOf("fruits" to listOf(listOf(listOf("apple"), "banana", "pineapple"))),
                result = Success(3)
            ),
            TestInput(
                expression = mapOf(
                    "size" to mapOf(
                        "if" to listOf(
                            mapOf("<" to listOf(mapOf("var" to "temp"), 0)),
                            "freezing",
                            mapOf("<" to listOf(mapOf("var" to "temp"), 100)),
                            listOf(listOf("liquid", "test", "b", "c")),
                            "gas"
                        )
                    )
                ),
                data = mapOf("temp" to 55),
                result = Success(4)
            ),
            TestInput(
                expression = mapOf(
                    "size" to mapOf(
                        "if" to listOf(
                            mapOf("<" to listOf(mapOf("var" to "temp"), 100)),
                            listOf("liquid", "test", "b", "c"),
                            "gas"
                        )
                    )
                ),
                data = mapOf("temp" to 55),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    "size" to 1
                ),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    "size" to 1521414312
                ),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    "size" to null
                ),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    "size" to "aa"
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
