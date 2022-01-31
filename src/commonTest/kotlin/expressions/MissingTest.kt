package expressions

import JsonLogicEngine
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class MissingTest : FunSpec({
    context("JsonLogic evaluation with only Missing operation") {
        withData(
            nameFn = { "Should apply ${it.data} on ${it.expression} result in ${it.result}"},
            // given
            ts = listOf(
                TestInput(expression = mapOf("missing" to emptyList<Any>()), data = null, result = emptyList<Any>()),
                TestInput(expression = mapOf("missing" to listOf("a")), data = null, result = listOf("a")),
                TestInput(
                    expression = mapOf("missing" to "a"),
                    data = mapOf("a" to "apple"),
                    result = emptyList<Any>()
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a")),
                    data = mapOf("a" to "apple"),
                    result = emptyList<Any>()
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a", "b")),
                    data = mapOf("a" to "apple"),
                    result = listOf("b")
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a", "b")),
                    data = mapOf("a" to "apple", "b" to "banana"),
                    result = emptyList<Any>()
                ),

                TestInput(
                    expression = mapOf("missing" to listOf("a", "b")),
                    data = emptyMap<String, Any>(),
                    result = listOf("a", "b")
                ),
                TestInput(expression = mapOf("missing" to listOf("a", "b")), data = null, result = listOf("a", "b")),
                TestInput(expression = mapOf("missing" to listOf("a.b")), data = null, result = listOf("a.b")),
                TestInput(
                    expression = mapOf("missing" to listOf("a.b")),
                    data = mapOf("a" to "apple"),
                    result = listOf("a.b")
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a.b")),
                    data = mapOf("a" to mapOf("c" to "apple cake")),
                    result = listOf("a.b")
                ),

                TestInput(
                    expression = mapOf("missing" to listOf("a.b")),
                    data = mapOf("a" to mapOf("b" to "apple brownie")),
                    result = emptyList<Any>()
                ),
                TestInput(
                    expression = mapOf("missing" to listOf("a.b", "a.c")),
                    data = mapOf("a" to mapOf("b" to "apple brownie")),
                    result = listOf("a.c")
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
