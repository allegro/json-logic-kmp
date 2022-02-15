package operations.logic

import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

// TODO extend unit tests cases
class StrictEqualsTest : FunSpec({
    context("JsonLogic evaluation with only StrictEquals operation") {
        withData(
            nameFn = { "Should apply ${it.data} on ${it.expression} result in ${it.result}" },
            // given
            ts = listOf(
                TestInput(mapOf("===" to listOf(1, 1)), null, true),
                TestInput(mapOf("===" to listOf(1, "1")), null, false),
                TestInput(mapOf("===" to listOf(1, 2)), null, false)
            )
        ) { (expression, data, result) ->
            // when
            val evaluationResult = JsonLogicEngine.instance.evaluate(expression, data)

            // then
            evaluationResult shouldBe result
        }
    }
})
