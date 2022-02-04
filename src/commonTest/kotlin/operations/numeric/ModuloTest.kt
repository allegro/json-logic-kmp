package operations.numeric

import JsonLogicEngine
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class ModuloTest : FunSpec({
    context("JsonLogic evaluation with only Modulo operation") {
        withData(
            nameFn = { "Should apply ${it.data} on ${it.expression} result in ${it.result}" },
            // given
            ts = listOf(
                TestInput(
                    expression = mapOf("%" to listOf(1, 2)),
                    data = emptyMap<String, Any>(),
                    result = 1
                ),
                TestInput(
                    expression = mapOf("%" to listOf(2, 2)),
                    data = emptyMap<String, Any>(),
                    result = 0
                ),
                TestInput(
                    expression = mapOf("%" to listOf(3, 2)),
                    data = emptyMap<String, Any>(),
                    result = 1
                )
            )
        ) { (expression, data, result) ->
            // when
            val evaluationResult = JsonLogicEngine.instance.evaluate(expression, data)

            // then
            evaluationResult shouldBe result
        }
    }
})
