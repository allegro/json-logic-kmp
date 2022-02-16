package operations.numeric

import JsonLogicEngine
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class MaxTest : FunSpec({
    context("JsonLogic evaluation with only Max operation") {
        withData(
            nameFn = { "Should apply ${it.data} on ${it.expression} result in ${it.result}" },
            // given
            ts = listOf(
                TestInput(
                    expression = mapOf("max" to listOf(1, 2, 3)),
                    result = 3
                ),
                TestInput(
                    expression = mapOf("max" to listOf(1, 3, 3)),
                    result = 3
                ),
                TestInput(
                    expression = mapOf("max" to listOf("-1", -2, "-3")),
                    result = -1
                ),
                TestInput(
                    expression = mapOf("max" to listOf(3, 2, 1)),
                    result = 3
                ),
                TestInput(
                    expression = mapOf("max" to listOf(1)),
                    result = 1
                ),
                TestInput(
                    expression = mapOf("max" to listOf(1, "2")),
                    result = 2
                ),
                TestInput(
                    expression = mapOf("max" to listOf(1, "banana")),
                    result = null
                ),
                TestInput(
                    expression = mapOf("max" to listOf(1, "banana", listOf(1, 2))),
                    result = null
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
