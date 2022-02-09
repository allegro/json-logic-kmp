package operations.string

import JsonLogicEngine
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class SubstrTest : FunSpec({
    context("JsonLogic evaluation with Cat operation") {
        withData(
            nameFn = { "Should apply ${it.data} on ${it.expression} result in ${it.result}" },
            // given
            ts = listOf(
                TestInput(
                    expression = mapOf("substr" to listOf("jsonlogic", 4)),
                    data = emptyMap<String, Any>(),
                    result = "logic"
                ),
                TestInput(
                    expression = mapOf("substr" to listOf("jsonlogic", -5)),
                    data = emptyMap<String, Any>(),
                    result = "logic"
                ),
                TestInput(
                    expression = mapOf("substr" to listOf("jsonlogic", 0, 1)),
                    data = emptyMap<String, Any>(),
                    result = "j"
                ),
                TestInput(
                    expression = mapOf("substr" to listOf("jsonlogic", -1, 1)),
                    data = emptyMap<String, Any>(),
                    result = "c"
                ),
                TestInput(
                    expression = mapOf("substr" to listOf("jsonlogic", 4, 5)),
                    data = emptyMap<String, Any>(),
                    result = "logic"
                ),
                TestInput(
                    expression = mapOf("substr" to listOf("jsonlogic", -5, 5)),
                    data = emptyMap<String, Any>(),
                    result = "logic"
                ),
                TestInput(
                    expression = mapOf("substr" to listOf("jsonlogic", -5, -2)),
                    data = emptyMap<String, Any>(),
                    result = "log"
                ),
                TestInput(
                    expression = mapOf("substr" to listOf("jsonlogic", 1, -5)),
                    data = emptyMap<String, Any>(),
                    result = "son"
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
