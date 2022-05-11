import JsonLogicResult.Failure
import JsonLogicResult.Success
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class LengthTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder()
        .addStandardOperation("length", Length).build()
    // given
    withData(
        nameFn = { input -> "Should evaluated ${input.expression} with given ${input.data} result in ${input.result}" },
        ts = listOf(
            TestInput(
                expression = mapOf("length" to "test"),
                result = Success(4)
            ),
            TestInput(
                expression = mapOf(
                    "length" to mapOf("var" to "fruits")
                ),
                data = mapOf("fruits" to listOf("apple")),
                result = Success(5)
            ),
            TestInput(
                expression = mapOf("length" to ""),
                result = Success(0)
            ),
            TestInput(
                expression = mapOf("length" to "this is very very very  very very very  very very very  very very very  very very very  very very very  very very very  very very very  very very very  very very very  very very very  very very very  very very very  very very very  very very very  very very very  very very very  very very very  very very very  very very very  long text"),
                result = Success(337)
            ),
            TestInput(
                expression = mapOf(
                    "length" to mapOf(
                        "if" to listOf(
                            mapOf("<" to listOf(mapOf("var" to "temp"), 0)), "freezing"
                        )
                    )
                ),
                data = mapOf("temp" to -20),
                result = Success(8)
            ),
            TestInput(
                expression = mapOf(
                    "length" to mapOf(
                        "if" to listOf(
                            mapOf("<" to listOf(mapOf("var" to "temp"), 0)), "freezing",
                            mapOf("<" to listOf(mapOf("var" to "temp"), 100)), "liquid",
                            "gas"
                        )
                    )
                ),
                data = mapOf("temp" to 55),
                result = Success(6)
            ),
            TestInput(
                expression = mapOf("length" to 123455),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf("length" to -123455),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf("length" to 0),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf("length" to 10.0),
                result = Failure.NullResult
            ),

            TestInput(
                expression = mapOf("length" to listOf("test", "test2")),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf("length" to mapOf("test" to "test2")),
                result = Failure.MissingOperation
            ),
        )
    ) { testInput: TestInput ->
        // when
        val evaluationResult = logicEngine.evaluate(testInput.expression, testInput.data)
        // then
        evaluationResult shouldBe testInput.result
    }
})
