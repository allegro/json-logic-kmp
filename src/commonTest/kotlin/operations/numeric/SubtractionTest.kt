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
                    result = -1
                ),
                TestInput(
                    expression = mapOf("-" to listOf(3, 2)),
                    result = 1
                ),
                TestInput(
                    expression = mapOf("-" to listOf(3)),
                    result = -3
                ),
                TestInput(
                    expression = mapOf("-" to listOf("1", 1)),
                    result = 0
                ),
                TestInput(
                    expression = mapOf("-" to listOf(0, 1)),
                    result = -1
                ),
                TestInput(
                    expression = mapOf("-" to listOf("1.3", 0)),
                    result = 1.3
                ),
                TestInput(
                    expression = mapOf("-" to listOf("1", 1.5, "banana")),
                    result = -0.5
                ),
                TestInput(
                    expression = mapOf("-" to listOf("1", 1, listOf("banana"))),
                    result = 0.0
                ),
                TestInput(
                    expression = mapOf("-" to listOf("5")),
                    result = -5
                ),
                TestInput(
                    expression = mapOf("-" to listOf("a", 2)),
                    result = null
                ),
                TestInput(
                    expression = mapOf("-" to listOf(listOf(2, "a"), 2)),
                    result = null
                ),
                TestInput(
                    expression = mapOf("-" to listOf(listOf("a", 2), 2)),
                    result = null
                ),
                TestInput(
                    expression = mapOf("-" to listOf(listOf(2, 2), 2)),
                    result = null
                ),
                TestInput(
                    expression = mapOf("-" to listOf(listOf(2, "a"), listOf("a", 2))),
                    result = null
                ),
                TestInput(
                    expression = mapOf("-" to listOf(listOf("5"), listOf("5"), listOf("5"))),
                    result = 0
                ),
                TestInput(
                    expression = mapOf("-" to listOf(listOf("5", listOf("5")), listOf("5"), listOf("5"))),
                    result = null
                ),
                TestInput(
                    expression = mapOf("-" to listOf(listOf("5"), 6)),
                    result = -1
                ),
                TestInput(
                    expression = mapOf("-" to listOf(listOf(listOf("5")), 6)),
                    result = -1
                ),
                TestInput(
                    expression = mapOf("-" to listOf(listOf(listOf("5")), listOf(6))),
                    result = -1
                ),
                TestInput(
                    expression = mapOf("-" to listOf(listOf(listOf("5"), listOf(6)))),
                    result = null
                ),
                TestInput(
                    expression = mapOf("-" to listOf(null, 5)),
                    result = -5
                ),
                TestInput(
                    expression = mapOf("-" to listOf(2, null)),
                    result = 2
                ),
                TestInput(
                    expression = mapOf("-" to listOf(null, null)),
                    result = 0
                ),
                TestInput(
                    expression = mapOf("-" to listOf(null)),
                    result = 0
                ),
                TestInput(
                    expression = mapOf("-" to listOf("banana")),
                    result = null
                ),
                TestInput(
                    expression = mapOf("-" to listOf(true, false)),
                    result = 1
                ),
                TestInput(
                    expression = mapOf("-" to listOf(true)),
                    result = -1
                ),
                TestInput(
                    expression = mapOf("-" to listOf(false)),
                    result = 0
                ),
                TestInput(
                    expression = mapOf("-" to listOf(true, null)),
                    result = 1
                ),
                TestInput(
                    expression = mapOf("-" to listOf(false, null)),
                    result = 0
                ),
                TestInput(
                    expression = mapOf("-" to listOf(false, true)),
                    result = -1
                ),
                TestInput(
                    expression = mapOf("-" to listOf(0, true)),
                    result = -1
                ),
                TestInput(
                    expression = mapOf("-" to listOf(1, true)),
                    result = 0
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
