package operations.string

import JsonLogicEngine
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class CatTest : FunSpec({
    context("JsonLogic evaluation with only Cat operation") {
        withData(
            nameFn = { "Should apply ${it.data} on ${it.expression} result in ${it.result}" },
            // given
            ts = listOf(
                TestInput(
                    expression = mapOf("cat" to "ice"),
                    data = emptyMap<String, Any>(),
                    result = "ice"
                ),
                TestInput(
                    expression = mapOf("cat" to listOf("ice")),
                    data = emptyMap<String, Any>(),
                    result = "ice"
                ),
                TestInput(
                    expression = mapOf("cat" to listOf("ice", "cream")),
                    data = emptyMap<String, Any>(),
                    result = "icecream"
                ),
                TestInput(
                    expression = mapOf("cat" to listOf(1, 2)),
                    data = emptyMap<String, Any>(),
                    result = "12"
                ),
                TestInput(
                    expression = mapOf("cat" to listOf("Robocop", 2)),
                    data = emptyMap<String, Any>(),
                    result = "Robocop2"
                ),
                TestInput(
                    expression = mapOf("cat" to listOf("Robocop", 2.0)),
                    data = emptyMap<String, Any>(),
                    result = "Robocop2"
                ),
                TestInput(
                    expression = mapOf("cat" to listOf("we all scream for ", "ice", "cream")),
                    data = emptyMap<String, Any>(),
                    result = "we all scream for icecream"
                ),
            )
        ) { (expression, data, result) ->
            // when
            val evaluationResult = JsonLogicEngine.instance.evaluate(expression, data)

            // then
            evaluationResult shouldBe result
        }
    }
})
