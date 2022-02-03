package operations.numeric

import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class LessThanTest : FunSpec({
    context("JsonLogic evaluation with only MissingSome operation") {
        withData(
            nameFn = { "Should apply ${it.data} on ${it.expression} result in ${it.result}"},
            // given
            ts = listOf(
                TestInput(
                    expression = mapOf("missing_some" to listOf(1, listOf("a", "b"))),
                    data = mapOf("a" to "apple"),
                    result = emptyList<Any>()
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
