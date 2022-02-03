package operations.numeric

import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class LessThanTest : FunSpec({
    context("JsonLogic evaluation with only LessThan operation") {
        withData(
            nameFn = { "Should apply ${it.data} on ${it.expression} result in ${it.result}"},
            // given
            ts = listOf(
                TestInput(
                    expression = mapOf("<" to listOf(2, 1)),
                    data = emptyMap<String, Any>(),
                    result = false
                ),
                TestInput(
                    expression = mapOf("<" to listOf(1, 1)),
                    data = emptyMap<String, Any>(),
                    result = false
                ),
                TestInput(
                    expression = mapOf("<" to listOf(1, 2)),
                    data = emptyMap<String, Any>(),
                    result = true
                ),
                TestInput(
                    expression = mapOf("<" to listOf("1", 2)),
                    data = emptyMap<String, Any>(),
                    result = true
                ),
                TestInput(
                    expression = mapOf("<" to listOf(1, 2, 3)),
                    data = emptyMap<String, Any>(),
                    result = true
                ),
                TestInput(
                    expression = mapOf("<" to listOf(1, 1, 3)),
                    data = emptyMap<String, Any>(),
                    result = false
                ),
                TestInput(
                    expression = mapOf("<" to listOf(1, 4, 3)),
                    data = emptyMap<String, Any>(),
                    result = false
                ),
                TestInput(
                    expression = mapOf("<" to listOf(4, 1, 3)),
                    data = emptyMap<String, Any>(),
                    result = false
                ),
                TestInput(
                    expression = mapOf("<" to listOf(4, 3, 1)),
                    data = emptyMap<String, Any>(),
                    result = false
                ),
                TestInput(
                    expression = mapOf("<" to listOf("4", 3, "1")),
                    data = emptyMap<String, Any>(),
                    result = false
                ),
                TestInput(
                    expression = mapOf("<" to listOf("banana", 3, "1")),
                    data = emptyMap<String, Any>(),
                    result = false
                ),
                TestInput(
                    expression = mapOf("<" to listOf(4, "3", 1)),
                    data = emptyMap<String, Any>(),
                    result = false
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
