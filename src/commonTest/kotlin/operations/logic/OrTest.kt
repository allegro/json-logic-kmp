package operations.logic

import JsonLogicEngine
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class OrTest : FunSpec({
    context("JsonLogic evaluation with only Or operation") {
        withData(
            nameFn = { "Should apply ${it.data} on ${it.expression} result in ${it.result}" },
            // given
            ts = listOf(
                TestInput(expression = mapOf("or" to listOf(true, true)), result = true),
                TestInput(expression = mapOf("or" to listOf(false, true)), result = true),
                TestInput(expression = mapOf("or" to listOf(true, false)), result = true),
                TestInput(expression = mapOf("or" to listOf(false, false)), result = false),
                TestInput(expression = mapOf("or" to listOf(false, false, true)), result = true),
                TestInput(expression = mapOf("or" to listOf(false, false, false)), result = false),
                TestInput(expression = mapOf("or" to listOf(false)), result = false),
                TestInput(expression = mapOf("or" to listOf(true)), result = true),
                TestInput(expression = mapOf("or" to listOf(1, 3)), result = 1),
                TestInput(expression = mapOf("or" to listOf(3, false)), result = 3),
                TestInput(expression = mapOf("or" to listOf(false, 3)), result = 3),
                TestInput(expression = mapOf("or" to listOf(emptyList<String>(), true)), result = true),
                TestInput(expression = mapOf("or" to listOf(0, true)), result = true),
                TestInput(expression = mapOf("or" to listOf("", true)), result = true),
                TestInput(expression = mapOf("or" to listOf("0", true)), result = "0"),
                TestInput(
                    expression = mapOf("or" to listOf("0", listOf("banana"))),
                    result = "0"
                ),
                TestInput(
                    expression = mapOf("or" to listOf(listOf("grapes"), listOf("banana"))),
                    result = listOf("grapes")
                ),
                TestInput(
                    expression = mapOf("or" to listOf(false, listOf("banana"))),
                    result = listOf("banana")
                ),
                TestInput(
                    expression = mapOf("or" to listOf(true, listOf("banana"))),
                    result = true
                ),
                TestInput(
                    expression = mapOf("or" to listOf(listOf(null), listOf("banana"))),
                    result = listOf(null)
                ),
                TestInput(expression = mapOf("or" to listOf(listOf(null), true)), result = listOf(null)),
                TestInput(expression = mapOf("or" to listOf(listOf(null), false)), result = listOf(null)),
                TestInput(
                    expression = mapOf("or" to listOf(listOf(emptyList<String>()), listOf("banana"))),
                    result = listOf(emptyList<String>())
                ),
                TestInput(
                    expression = mapOf("or" to listOf(listOf(emptyList<String>()), listOf(null))),
                    result = listOf(emptyList<String>())
                ),
                TestInput(
                    expression = mapOf("or" to listOf(listOf(emptyList<String>()), listOf(null), listOf("banana"))),
                    result = listOf(emptyList<String>())
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
