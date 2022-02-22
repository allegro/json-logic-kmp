package operations.logic

import JsonLogicEngine
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class NotStrictEqualsTest : FunSpec({
    context("JsonLogic evaluation with only NotStrictEquals operation") {
        withData(
            nameFn = { "Should apply ${it.data} on ${it.expression} result in ${it.result}" },
            // given
            ts = listOf(
                TestInput(expression = mapOf("!==" to listOf(1, 1)), result = false),
                TestInput(expression = mapOf("!==" to listOf(1, "1")), result = true),
                TestInput(expression = mapOf("!==" to listOf(1, 2)), result = true),
                TestInput(expression = mapOf("!==" to listOf(null, 2)), result = true),
                TestInput(expression = mapOf("!==" to listOf(null, true)), result = true),
                TestInput(expression = mapOf("!==" to listOf(null, false)), result = true),
                TestInput(expression = mapOf("!==" to listOf(null, "false")), result = true),
                TestInput(expression = mapOf("!==" to listOf(null, "true")), result = true),
                TestInput(expression = mapOf("!==" to listOf(false, "false")), result = true),
                TestInput(expression = mapOf("!==" to listOf(false, listOf("false"))), result = true),
                TestInput(expression = mapOf("!==" to listOf(false, listOf(false))), result = true),
                TestInput(
                    expression = mapOf("!==" to listOf(emptyList<Any>(), listOf(emptyList<Any>()))),
                    result = true
                ),
                TestInput(expression = mapOf("!==" to listOf(emptyList<Any>(), null)), result = true),
                TestInput(expression = mapOf("!==" to listOf(emptyList(), emptyList<Any>())), result = true),
                TestInput(expression = mapOf("!==" to listOf("null", null)), result = true),
                TestInput(expression = mapOf("!==" to listOf(true, "true")), result = true),
                TestInput(expression = mapOf("!==" to listOf(1, null)), result = true),
                TestInput(expression = mapOf("!==" to listOf(listOf("banana"), null)), result = true),
                TestInput(expression = mapOf("!==" to listOf(listOf("banana"), true)), result = true),
                TestInput(expression = mapOf("!==" to listOf(listOf("banana"), false)), result = true),
                TestInput(expression = mapOf("!==" to listOf(true, false)), result = true),
                TestInput(expression = mapOf("!==" to listOf(false, true)), result = true),
                TestInput(expression = mapOf("!==" to listOf(true, true)), result = false),
                TestInput(expression = mapOf("!==" to listOf(1, listOf("1"))), result = true),
                TestInput(expression = mapOf("!==" to listOf(1, listOf(1))), result = true),
                TestInput(expression = mapOf("!==" to listOf(1, false)), result = true),
                TestInput(expression = mapOf("!==" to listOf(-1, false)), result = true),
                TestInput(expression = mapOf("!==" to listOf(0, false)), result = true),
                TestInput(expression = mapOf("!==" to listOf(0, true)), result = true),
                TestInput(expression = mapOf("!==" to listOf(1, true)), result = true),
                TestInput(expression = mapOf("!==" to listOf(-1, true)), result = true),
                TestInput(expression = mapOf("!==" to listOf(true, true, "false")), result = false),
                TestInput(expression = mapOf("!==" to listOf(1, 0, 1)), result = true),
                TestInput(expression = mapOf("!==" to listOf(1)), result = true),
                TestInput(expression = mapOf("!==" to listOf(true)), result = true),
                TestInput(expression = mapOf("!==" to true), result = true),
                TestInput(expression = mapOf("!==" to false), result = true),
                TestInput(expression = mapOf("!==" to listOf("true")), result = true),
                TestInput(expression = mapOf("!==" to listOf("banana")), result = true),
                TestInput(expression = mapOf("!==" to "banana"), result = true),
                TestInput(expression = mapOf("!==" to listOf(null)), result = true),
                TestInput(expression = mapOf("!==" to null), result = true),
                TestInput(expression = mapOf("!==" to ""), result = true),
                TestInput(expression = mapOf("!==" to "     "), result = true),
                TestInput(expression = mapOf("!==" to emptyList<Any>()), result = false),
                TestInput(expression = mapOf("!==" to listOf(emptyList<Any>())), result = true),
                TestInput(expression = mapOf("!==" to listOf(null, null)), result = false),
                TestInput(expression = mapOf("!==" to listOf(1, listOf(listOf("1")))), result = true),
                TestInput(expression = mapOf("!==" to listOf("banana", listOf(listOf("banana")))), result = true),
                TestInput(expression = mapOf("!==" to listOf("banana", listOf("banana", "banana"))), result = true),
                TestInput(expression = mapOf("!==" to listOf(listOf(null), listOf(null))), result = true),
                TestInput(expression = mapOf("!==" to listOf(null, listOf(null))), result = true),
                TestInput(expression = mapOf("!==" to listOf(1, listOf(null))), result = true),
                TestInput(expression = mapOf("!==" to listOf(0, listOf(null))), result = true),
                TestInput(expression = mapOf("!==" to listOf(true, listOf(null))), result = true),
                TestInput(expression = mapOf("!==" to listOf(false, listOf(null))), result = true),
                TestInput(expression = mapOf("!==" to listOf(-1, listOf(null))), result = true),
                TestInput(expression = mapOf("!==" to listOf(0.5, listOf(null))), result = true),
                TestInput(expression = mapOf("!==" to listOf("", "")), result = false),
                TestInput(expression = mapOf("!==" to listOf("", "    ")), result = true),
                TestInput(expression = mapOf("!==" to listOf("", emptyList<String>())), result = true),
                TestInput(expression = mapOf("!==" to listOf("", listOf(""))), result = true),
                TestInput(expression = mapOf("!==" to listOf(listOf(""), listOf(""))), result = true),
                TestInput(expression = mapOf("!==" to listOf("", listOf(listOf("")))), result = true),
                )
        ) { (expression, data, result) ->
            // when
            val evaluationResult = JsonLogicEngine.instance.evaluate(expression, data)

            // then
            evaluationResult shouldBe result
        }
    }
})
