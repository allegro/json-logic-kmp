package operations.numeric

import JsonLogicEngine
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class MultiplicationTest : FunSpec({
    context("JsonLogic evaluation with only Multiplication operation") {
        withData(
            nameFn = { "Should apply ${it.data} on ${it.expression} result in ${it.result}" },
            // given
            ts = listOf(
                TestInput(
                    expression = mapOf("*" to listOf(3, 2)),
                    data = emptyMap<String, Any>(),
                    result = 6
                ),
                TestInput(
                    expression = mapOf("*" to listOf(2, 2, 2)),
                    data = emptyMap<String, Any>(),
                    result = 8
                ),
                TestInput(
                    expression = mapOf("*" to listOf(1)),
                    data = emptyMap<String, Any>(),
                    result = 1
                ),
                TestInput(
                    expression = mapOf("*" to listOf("1", 1)),
                    data = emptyMap<String, Any>(),
                    result = 1
                ),
                TestInput(
                    expression = mapOf("*" to listOf("1.7", 3)),
                    data = emptyMap<String, Any>(),
                    result = 5.1
                ),
                TestInput(
                    expression = mapOf("*" to listOf(2, 0)),
                    data = emptyMap<String, Any>(),
                    result = 0
                ),
                TestInput(
                    expression = mapOf("*" to listOf(0, 2)),
                    data = emptyMap<String, Any>(),
                    result = 0
                ),
                TestInput(
                    expression = mapOf("*" to listOf("1.3", "3.7")),
                    data = emptyMap<String, Any>(),
                    result = 4.81
                ),
                TestInput(
                    expression = mapOf("*" to listOf("2", 1.5, "banana")),
                    data = emptyMap<String, Any>(),
                    result = null
                ),
                TestInput(
                    expression = mapOf("*" to listOf("2", 1.5, listOf("banana"))),
                    data = emptyMap<String, Any>(),
                    result = null
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
