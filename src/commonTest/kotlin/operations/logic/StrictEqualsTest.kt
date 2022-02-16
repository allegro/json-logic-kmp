package operations.logic

import JsonLogicEngine
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class StrictEqualsTest : FunSpec({
    context("JsonLogic evaluation with only StrictEquals operation") {
        withData(
            nameFn = { "Should apply ${it.data} on ${it.expression} result in ${it.result}" },
            // given
            ts = listOf(
                TestInput(expression = mapOf("===" to listOf(1, 1)), result = true),
                TestInput(expression = mapOf("===" to listOf(1, "1")), result = false),
                TestInput(expression = mapOf("===" to listOf(1, 2)), result = false),
                TestInput(expression = mapOf("===" to listOf(null, 2)), result = false),
                TestInput(expression = mapOf("===" to listOf(null, true)), result = false),
                TestInput(expression = mapOf("===" to listOf(null, false)), result = false),
                TestInput(expression = mapOf("===" to listOf(null, "false")), result = false),
                TestInput(expression = mapOf("===" to listOf(null, "true")), result = false),
                TestInput(expression = mapOf("===" to listOf(false, "false")), result = false),
                TestInput(expression = mapOf("===" to listOf(false, listOf("false"))), result = false),
                TestInput(expression = mapOf("===" to listOf(false, listOf(false))), result = false),
                TestInput(
                    expression = mapOf("===" to listOf(emptyList<Any>(), listOf(emptyList<Any>()))),
                    result = false
                ),
                TestInput(expression = mapOf("===" to listOf(emptyList<Any>(), null)), result = false),
                TestInput(expression = mapOf("===" to listOf(emptyList(), emptyList<Any>())), result = false),
                TestInput(expression = mapOf("===" to listOf("null", null)), result = false),
                TestInput(expression = mapOf("===" to listOf(true, "true")), result = false),
                TestInput(expression = mapOf("===" to listOf(1, null)), result = false),
                TestInput(expression = mapOf("===" to listOf(listOf("banana"), null)), result = false),
                TestInput(expression = mapOf("===" to listOf(listOf("banana"), true)), result = false),
                TestInput(expression = mapOf("===" to listOf(listOf("banana"), false)), result = false),
                TestInput(expression = mapOf("===" to listOf(true, false)), result = false),
                TestInput(expression = mapOf("===" to listOf(false, true)), result = false),
                TestInput(expression = mapOf("===" to listOf(true, true)), result = true),
                TestInput(expression = mapOf("===" to listOf(1, listOf("1"))), result = false),
                TestInput(expression = mapOf("===" to listOf(1, listOf(1))), result = false),
                TestInput(expression = mapOf("===" to listOf(1, false)), result = false),
                TestInput(expression = mapOf("===" to listOf(-1, false)), result = false),
                TestInput(expression = mapOf("===" to listOf(0, false)), result = false),
                TestInput(expression = mapOf("===" to listOf(0, true)), result = false),
                TestInput(expression = mapOf("===" to listOf(1, true)), result = false),
                TestInput(expression = mapOf("===" to listOf(-1, true)), result = false),
            )
        ) { (expression, data, result) ->
            // when
            val evaluationResult = JsonLogicEngine.instance.evaluate(expression, data)

            // then
            evaluationResult shouldBe result
        }
    }
})
