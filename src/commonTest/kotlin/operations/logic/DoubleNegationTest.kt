package operations.logic

import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

// TODO extend unit tests cases
class DoubleNegationTest : FunSpec({
    context("JsonLogic evaluation with only DoubleNegation operation") {
        withData(
            nameFn = { "Should apply ${it.data} on ${it.expression} result in ${it.result}" },
            // given
            ts = listOf(
                TestInput(expression = mapOf("!!" to listOf(false)), result = false),
                TestInput(expression = mapOf("!!" to listOf(true)), result = true),
                TestInput(expression = mapOf("!!" to false), result = false),
                TestInput(expression = mapOf("!!" to "false"), result = true),
                TestInput(expression = mapOf("!!" to true), result = true),
                TestInput(expression = mapOf("!!" to 0), result = false),
                TestInput(expression = mapOf("!!" to 1), result = true),
                TestInput(expression = mapOf("!!" to listOf(emptyList<Boolean>())), result = false),
                TestInput(expression = mapOf("!!" to listOf(0)), result = false),
                TestInput(expression = mapOf("!!" to listOf("")), result = false),
                TestInput(expression = mapOf("!!" to listOf("0")), result = true),
            )
        ) { (expression, data, result) ->
            // when
            val evaluationResult = JsonLogicEngine.instance.evaluate(expression, data)

            // then
            evaluationResult shouldBe result
        }
    }
})
