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
                TestInput(expression = mapOf("and" to listOf(true, false)), data = null, result = false),
                TestInput(expression = mapOf("and" to listOf(true, true)), data = null, result = true),
                TestInput(expression = mapOf("and" to listOf(false, true)), data = null, result = false),
                TestInput(expression = mapOf("and" to listOf(false, false)), data = null, result = false),
                TestInput(expression = mapOf("and" to listOf(true, true, true)), data = null, result = true),
                TestInput(expression = mapOf("and" to listOf(true, true, false)), data = null, result = false),
                TestInput(expression = mapOf("and" to listOf(false)), data = null, result = false),
                TestInput(expression = mapOf("and" to listOf(true)), data = null, result = true),
                TestInput(expression = mapOf("and" to listOf(1, 3)), data = null, result = 3),
                TestInput(expression = mapOf("and" to listOf(3, false)), data = null, result = false),
                TestInput(expression = mapOf("and" to listOf(false, 3)), data = null, result = false),
                TestInput(
                    expression = mapOf("and" to listOf(emptyList<Any>(), true)),
                    data = null,
                    result = emptyList<Any>()
                ),
                TestInput(expression = mapOf("and" to listOf(0, true)), data = null, result = 0),
                TestInput(expression = mapOf("and" to listOf("", true)), data = null, result = ""),
                TestInput(expression = mapOf("and" to listOf("0", true)), data = null, result = true),
                TestInput(
                    expression = mapOf("and" to listOf(mapOf(">" to listOf(3, 1)), true)),
                    data = null,
                    result = true
                ),
                TestInput(
                    expression = mapOf("and" to listOf(mapOf(">" to listOf(3, 1)), false)),
                    data = null,
                    result = false
                ),
                TestInput(
                    expression = mapOf("and" to listOf(mapOf(">" to listOf(3, 1)), mapOf("!" to true))),
                    data = null,
                    result = false
                ),
                TestInput(
                    expression = mapOf("and" to listOf(mapOf(">" to listOf(3, 1)), mapOf("<" to listOf(1, 3)))),
                    data = null,
                    result = true
                ),
                TestInput(
                    expression = mapOf("and" to listOf("0", listOf("banana"))),
                    data = null,
                    result = listOf("banana")
                ),
                TestInput(
                    expression = mapOf("and" to listOf(listOf("grapes"), listOf("banana"))),
                    data = null,
                    result = listOf("banana")
                ),
                TestInput(expression = mapOf("and" to listOf(false, listOf("banana"))), data = null, result = false),
                TestInput(
                    expression = mapOf("and" to listOf(true, listOf("banana"))),
                    data = null,
                    result = listOf("banana")
                ),
                TestInput(
                    expression = mapOf("and" to listOf(listOf(null), listOf("banana"))),
                    data = null,
                    result = listOf("banana")
                ),
                TestInput(expression = mapOf("and" to listOf(listOf(null), true)), data = null, result = true),
                TestInput(expression = mapOf("and" to listOf(listOf(null), false)), data = null, result = false),
                TestInput(
                    expression = mapOf("and" to listOf(listOf(emptyList<String>()), listOf("banana"))),
                    data = null,
                    result = listOf("banana")
                ),
                TestInput(
                    expression = mapOf("and" to listOf(listOf(emptyList<String>()), listOf(null))),
                    data = null,
                    result = listOf(null)
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
