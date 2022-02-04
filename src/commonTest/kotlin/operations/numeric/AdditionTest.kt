package operations.numeric

import JsonLogicEngine
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class AdditionTest : FunSpec({
    context("JsonLogic evaluation with only Addition operation") {
        withData(
            nameFn = { "Should apply ${it.data} on ${it.expression} result in ${it.result}" },
            // given
            ts = listOf(
                TestInput(
                    expression = mapOf("+" to listOf(1, 2)),
                    data = emptyMap<String, Any>(),
                    result = 3
                ),
                TestInput(
                    expression = mapOf("+" to listOf(2, 2, 2)),
                    data = emptyMap<String, Any>(),
                    result = 6
                ),
                TestInput(
                    expression = mapOf("+" to listOf(1)),
                    data = emptyMap<String, Any>(),
                    result = 1
                ),
                TestInput(
                    expression = mapOf("+" to listOf("1", 1)),
                    data = emptyMap<String, Any>(),
                    result = 2
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
