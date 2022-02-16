package operations.logic

import JsonLogicEngine
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

// TODO extend unit tests cases
class OrTest : FunSpec({
    context("JsonLogic evaluation with only Or operation") {
        withData(
            nameFn = { "Should apply ${it.data} on ${it.expression} result in ${it.result}" },
            // given
            ts = listOf(
                TestInput(expression = mapOf("or" to listOf(true, true)), data = null, result = true),
                TestInput(expression = mapOf("or" to listOf(false, true)), data = null, result = true),
                TestInput(expression = mapOf("or" to listOf(true, false)), data = null, result = true),
                TestInput(expression = mapOf("or" to listOf(false, false)), data = null, result = false),
                TestInput(expression = mapOf("or" to listOf(false, false, true)), data = null, result = true),
                TestInput(expression = mapOf("or" to listOf(false, false, false)), data = null, result = false),
                TestInput(expression = mapOf("or" to listOf(false)), data = null, result = false),
                TestInput(expression = mapOf("or" to listOf(true)), data = null, result = true),
                TestInput(expression = mapOf("or" to listOf(1, 3)), data = null, result = 1),
                TestInput(expression = mapOf("or" to listOf(3, false)), data = null, result = 3),
                TestInput(expression = mapOf("or" to listOf(false, 3)), data = null, result = 3),
                TestInput(expression = mapOf("or" to listOf(emptyList<String>(), true)), data = null, result = true),
                TestInput(expression = mapOf("or" to listOf(0, true)), data = null, result = true),
                TestInput(expression = mapOf("or" to listOf("", true)), data = null, result = true),
                TestInput(expression = mapOf("or" to listOf("0", true)), data = null, result = "0"),
                TestInput(
                    expression = mapOf("or" to listOf("0", listOf("banana"))),
                    data = null,
                    result = "0"
                ),
                TestInput(
                    expression = mapOf("or" to listOf(listOf("grapes"), listOf("banana"))),
                    data = null,
                    result = listOf("grapes")
                ),
                TestInput(
                    expression = mapOf("or" to listOf(false, listOf("banana"))),
                    data = null,
                    result = listOf("banana")
                ),
                TestInput(
                    expression = mapOf("or" to listOf(true, listOf("banana"))),
                    data = null,
                    result = true
                ),
                TestInput(
                    expression = mapOf("or" to listOf(listOf(null), listOf("banana"))),
                    data = null,
                    result = listOf(null)
                ),
                TestInput(expression = mapOf("or" to listOf(listOf(null), true)), data = null, result = listOf(null)),
                TestInput(expression = mapOf("or" to listOf(listOf(null), false)), data = null, result = listOf(null)),
                TestInput(
                    expression = mapOf("or" to listOf(listOf(emptyList<String>()), listOf("banana"))),
                    data = null,
                    result = listOf(emptyList<String>())
                ),
                TestInput(
                    expression = mapOf("or" to listOf(listOf(emptyList<String>()), listOf(null))),
                    data = null,
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
