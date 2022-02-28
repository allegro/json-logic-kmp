package operations.logic

import JsonLogicEngine
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class NegationTest : FunSpec({
    context("JsonLogic evaluation with only Negation operation") {
        withData(
            nameFn = { "Should apply ${it.data} on ${it.expression} result in ${it.result}" },
            // given
            ts = listOf(
                TestInput(expression = mapOf("!" to listOf(false)), result = true),
                TestInput(expression = mapOf("!" to listOf(true)), result = false),
                TestInput(expression = mapOf("!" to "false"), result = false),
                TestInput(expression = mapOf("!" to false), result = true),
                TestInput(expression = mapOf("!" to true), result = false),
                TestInput(expression = mapOf("!" to 0), result = true),
                TestInput(expression = mapOf("!" to 1), result = false),
                TestInput(expression = mapOf("!" to listOf(emptyList<Boolean>())), result = true),
                TestInput(expression = mapOf("!" to listOf(0)), result = true),
                TestInput(expression = mapOf("!" to listOf("")), result = true),
                TestInput(expression = mapOf("!" to listOf("0")), result = false),
                TestInput(expression = mapOf("!" to listOf(null)), result = true),
                TestInput(expression = mapOf("!" to listOf("banana", null)), result = false),
                TestInput(expression = mapOf("!" to listOf(13)), result = false),
                TestInput(expression = mapOf("!" to listOf(false, false)), result = true),
                TestInput(expression = mapOf("!" to listOf(true, true)), result = false),
                TestInput(expression = mapOf("!" to listOf(true, null)), result = false),
                TestInput(expression = mapOf("!" to listOf(null, null)), result = true),
            )
        ) { (expression, data, result) ->
            // when
            val evaluationResult = JsonLogicEngine.instance.evaluate(expression, data)

            // then
            evaluationResult shouldBe result
        }
    }
})
