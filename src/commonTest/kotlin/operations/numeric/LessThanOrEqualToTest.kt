package operations.numeric

import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class LessThanOrEqualToTest : FunSpec({
    context("JsonLogic evaluation with only LessThanOrEqualTo operation") {
        withData(
            nameFn = { "Should apply ${it.data} on ${it.expression} result in ${it.result}"},
            // given
            ts = listOf(
                TestInput(
                    expression = mapOf("<=" to listOf(2, 1)),
                    data = emptyMap<String, Any>(),
                    result = false
                ),
                TestInput(
                    expression = mapOf("<=" to listOf(1, 1)),
                    data = emptyMap<String, Any>(),
                    result = true
                ),
                TestInput(
                    expression = mapOf("<=" to listOf(1, 2)),
                    data = emptyMap<String, Any>(),
                    result = true
                ),
                TestInput(
                    expression = mapOf("<=" to listOf("1", 2)),
                    data = emptyMap<String, Any>(),
                    result = true
                ),
                TestInput(
                    expression = mapOf("<=" to listOf(1, 2, 3)),
                    data = emptyMap<String, Any>(),
                    result = true
                ),
                TestInput(
                    expression = mapOf("<=" to listOf(1, 4, 3)),
                    data = emptyMap<String, Any>(),
                    result = false
                ),

                TestInput(
                    expression = mapOf("<=" to listOf(4, 1, 3)),
                    data = emptyMap<String, Any>(),
                    result = false
                ),
                TestInput(
                    expression = mapOf("<=" to listOf(4, 3, 1)),
                    data = emptyMap<String, Any>(),
                    result = false
                ),
                TestInput(
                    expression = mapOf("<=" to listOf("4", 3, "1")),
                    data = emptyMap<String, Any>(),
                    result = false
                ),
                TestInput(
                    expression = mapOf("<=" to listOf("banana", 3, "1")),
                    data = emptyMap<String, Any>(),
                    result = false
                ),
                TestInput(
                    expression = mapOf("<=" to listOf(4, "3", 1)),
                    data = emptyMap<String, Any>(),
                    result = false
                ),
//                TestInput(
//                    expression = mapOf("<=" to listOf(0, mapOf("var" to "temp"), 100)),
//                    data = mapOf("temp" to 37),
//                    result = true
//                ),
            )
        ) { (expression, data, result) ->
            // when
            val evaluationResult = JsonLogicEngine.instance.evaluate(expression, data)

            // then
            evaluationResult shouldBe result
        }
    }
})
