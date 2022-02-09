package operations.numeric

import JsonLogicEngine
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class SubtractionTest : FunSpec({
    context("JsonLogic evaluation with only Subtraction operation") {
        withData(
            nameFn = { "Should apply ${it.data} on ${it.expression} result in ${it.result}" },
            // given
            ts = listOf(
                TestInput(
                    expression = mapOf("-" to listOf(2, 3)),
                    data = emptyMap<String, Any>(),
                    result = -1
                ),
                TestInput(
                    expression = mapOf("-" to listOf(3, 2)),
                    data = emptyMap<String, Any>(),
                    result = 1
                ),
                TestInput(
                    expression = mapOf("-" to listOf(3)),
                    data = emptyMap<String, Any>(),
                    result = -3
                ),
                TestInput(
                    expression = mapOf("-" to listOf("1", 1)),
                    data = emptyMap<String, Any>(),
                    result = 0
                ),
                TestInput(
                    expression = mapOf("-" to listOf(0, 1)),
                    data = emptyMap<String, Any>(),
                    result = -1
                ),
                TestInput(
                    expression = mapOf("-" to listOf("1.3", 0)),
                    data = emptyMap<String, Any>(),
                    result = 1.3
                ),
                TestInput(
                    expression = mapOf("-" to listOf("1", 1.5, "banana")),
                    data = emptyMap<String, Any>(),
                    result = -0.5
                ),
                TestInput(
                    expression = mapOf("-" to listOf("1", 1, listOf("banana"))),
                    data = emptyMap<String, Any>(),
                    result = 0.0
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

@Suppress("unused")
private val defectiveTestCases = listOf(
    TestInput(
        expression = mapOf("-" to listOf("2.3", 3.2)),
        data = emptyMap<String, Any>(),
        result = -0.9
    ),
)
