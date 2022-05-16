package string

import JsonLogicEngine
import TestInput
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
                result = JsonLogicResult.Success(4)
            ),
            TestInput(
                expression = mapOf(
                    "length" to mapOf("var" to "fruits")
                ),
                data = mapOf("fruits" to listOf("apple")),
                result = JsonLogicResult.Success(5)
            ),
            TestInput(
                expression = mapOf("length" to ""),
                result = JsonLogicResult.Success(0)
            ),
            TestInput(
                expression = mapOf(
                    "length" to "this is very very very  very very very  very very very  very very " +
                        " very very very  very very very  very very very  very very very  very very very  very very" +
                        " very very  very very very  very very very  very very very  very very very  very very very" +
                        " very very very  very very very  very very very  very very very  very very very  long text"
                ),
                result = JsonLogicResult.Success(336)
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
                result = JsonLogicResult.Success(8)
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
                result = JsonLogicResult.Success(6)
            ),
            TestInput(
                expression = mapOf("length" to 123455),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf("length" to -123455),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf("length" to 0),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf("length" to 10.0),
                result = JsonLogicResult.Failure.NullResult
            ),

            TestInput(
                expression = mapOf("length" to listOf("test", "test2")),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf("length" to mapOf("test" to "test2")),
                result = JsonLogicResult.Failure.MissingOperation
            ),
        )
    ) { testInput: TestInput ->
        // when
        val evaluationResult = logicEngine.evaluate(testInput.expression, testInput.data)
        // then
        evaluationResult shouldBe testInput.result
    }
})
