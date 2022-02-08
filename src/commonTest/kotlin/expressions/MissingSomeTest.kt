package expressions

import JsonLogicEngine
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class MissingSomeTest : FunSpec({
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
                TestInput(
                    expression = mapOf("missing_some" to listOf(1, listOf("a", "b"))),
                    data = mapOf("b" to "banana"),
                    result = emptyList<Any>()
                ),
                TestInput(
                    expression = mapOf("missing_some" to listOf(1, listOf("a", "b"))),
                    data = mapOf("a" to "apple", "b" to "banana"),
                    result = emptyList<Any>()
                ),
                TestInput(
                    expression = mapOf("missing_some" to listOf(1, listOf("a", "b"))),
                    data = mapOf("c" to "carrot"),
                    result = listOf("a", "b")
                ),
                TestInput(
                    expression = mapOf("missing_some" to listOf(2, listOf("a", "b", "c"))),
                    data = mapOf("a" to "apple", "b" to "banana"),
                    result = emptyList<Any>()
                ),
                TestInput(
                    expression = mapOf("missing_some" to listOf(2, listOf("a", "b", "c"))),
                    data = mapOf("a" to "apple", "c" to "carrot"),
                    result = emptyList<Any>()
                ),
                TestInput(
                    expression = mapOf("missing_some" to listOf(2, listOf("a", "b", "c"))),
                    data = mapOf("a" to "apple", "b" to "banana", "c" to "carrot"),
                    result = emptyList<Any>()
                ),
                TestInput(
                    expression = mapOf("missing_some" to listOf(2, listOf("a", "b", "c"))),
                    data = mapOf("a" to "apple", "d" to "durian"),
                    result = listOf("b", "c")
                ),
                TestInput(
                    expression = mapOf("missing_some" to listOf(2, listOf("a", "b", "c"))),
                    data = mapOf("d" to "durian", "e" to "eggplant"),
                    result = listOf("a", "b", "c")
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
