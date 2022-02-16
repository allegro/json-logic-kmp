package operations.logic

import JsonLogicEngine
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

// TODO extend unit tests cases
class AndTest : FunSpec({
    context("JsonLogic evaluation with And operation") {
        withData(
            nameFn = { "Should apply ${it.data} on ${it.expression} result in ${it.result}" },
            // given
            ts = listOf(
                TestInput(expression = mapOf("and" to listOf(true, false)), result = false),
                TestInput(expression = mapOf("and" to listOf(true, true)), result = true),
                TestInput(expression = mapOf("and" to listOf(false, true)), result = false),
                TestInput(expression = mapOf("and" to listOf(false, false)), result = false),
                TestInput(expression = mapOf("and" to listOf(true, true, true)), result = true),
                TestInput(expression = mapOf("and" to listOf(true, true, false)), result = false),
                TestInput(expression = mapOf("and" to listOf(false)), result = false),
                TestInput(expression = mapOf("and" to listOf(true)), result = true),
                TestInput(expression = mapOf("and" to listOf(1, 3)), result = 3),
                TestInput(expression = mapOf("and" to listOf(3, false)), result = false),
                TestInput(expression = mapOf("and" to listOf(false, 3)), result = false),
                TestInput(
                    expression = mapOf("and" to listOf(emptyList<Any>(), true)),
                    result = emptyList<Any>()
                ),
                TestInput(expression = mapOf("and" to listOf(0, true)), result = 0),
                TestInput(expression = mapOf("and" to listOf("", true)), result = ""),
                TestInput(expression = mapOf("and" to listOf("0", true)), result = true),
                TestInput(
                    expression = mapOf("and" to listOf(mapOf(">" to listOf(3, 1)), true)),
                    result = true
                ),
                TestInput(
                    expression = mapOf("and" to listOf(mapOf(">" to listOf(3, 1)), false)),
                    result = false
                ),
                TestInput(
                    expression = mapOf("and" to listOf(mapOf(">" to listOf(3, 1)), mapOf("!" to true))),
                    result = false
                ),
                TestInput(
                    expression = mapOf("and" to listOf(mapOf(">" to listOf(3, 1)), mapOf("<" to listOf(1, 3)))),
                    result = true
                ),
                TestInput(
                    expression = mapOf("and" to listOf("0", listOf("banana"))),
                    result = listOf("banana")
                ),
                TestInput(
                    expression = mapOf("and" to listOf(listOf("grapes"), listOf("banana"))),
                    result = listOf("banana")
                ),
                TestInput(expression = mapOf("and" to listOf(false, listOf("banana"))), result = false),
                TestInput(
                    expression = mapOf("and" to listOf(true, listOf("banana"))),
                    result = listOf("banana")
                ),
                TestInput(
                    expression = mapOf("and" to listOf(listOf(null), listOf("banana"))),
                    result = listOf("banana")
                ),
                TestInput(expression = mapOf("and" to listOf(listOf(null), true)), result = true),
                TestInput(expression = mapOf("and" to listOf(listOf(null), false)), result = false),
                TestInput(
                    expression = mapOf("and" to listOf(listOf(emptyList<String>()), listOf("banana"))),
                    result = listOf("banana")
                ),
                TestInput(
                    expression = mapOf("and" to listOf(listOf(emptyList<String>()), listOf(null))),
                    result = listOf(null)
                ),
                TestInput(
                    expression = mapOf("and" to listOf(listOf(emptyList<String>()), listOf(null), listOf("banana"))),
                    result = listOf("banana")
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
