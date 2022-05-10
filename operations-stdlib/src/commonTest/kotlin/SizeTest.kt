import JsonLogicResult.Success
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class SizeTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().addStandardOperation("size", Size).build()

    withData(
        nameFn = { input -> "Should evaluated ${input.expression} with given ${input.data} result in ${input.result}" },
        ts = listOf(
            TestInput(
                expression = mapOf("size" to "test"),
                result = Success(1)
            ),
            TestInput(
                expression = mapOf("size" to listOf("test", "test2")),
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
                result = Success(2)
            ),
            TestInput(
                expression = mapOf(
                    "size" to mapOf("var" to "fruits")
                ),
                data = mapOf("fruits" to listOf(listOf("apple"), "banana", "pineapple")),
                result = Success(3)
            ),
            TestInput(
                expression = mapOf(
                    "size" to 1
                ),
                result = Success(1)
            ),
                TestInput(
                expression = mapOf(
                    "size" to 1521414312
                ),
            result = Success(1)
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
