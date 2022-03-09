package operations.string

import JsonLogicEngine
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class InTest : FunSpec({
    context("JsonLogic evaluation with only In operation") {
        withData(
            nameFn = { "Should apply ${it.data} on ${it.expression} result in ${it.result}" },
            // given
            ts = listOf(
                TestInput(
                    expression = mapOf(
                        "in" to listOf(
                            mapOf("var" to "filling"),
                            listOf("apple", "cherry")
                        )
                    ),
                    data = mapOf("filling" to "apple"),
                    result = true
                ),
                TestInput(
                    expression = mapOf("in" to listOf("Bart", listOf("Bart", "Homer", "Lisa", "Marge", "Maggie"))),
                    data = emptyMap<String, Any>(),
                    result = true
                ),
                TestInput(
                    expression = mapOf("in" to listOf("Milhouse", listOf("Bart", "Homer", "Lisa", "Marge", "Maggie"))),
                    data = emptyMap<String, Any>(),
                    result = false
                ),
                TestInput(
                    expression = mapOf("in" to listOf("Spring", "Springfield")),
                    data = emptyMap<String, Any>(),
                    result = true
                ),
                TestInput(
                    expression = mapOf("in" to listOf("i", "team")),
                    data = emptyMap<String, Any>(),
                    result = false
                ),
                TestInput(
                    expression = mapOf("in" to listOf("t", listOf("team"))),
                    data = emptyMap<String, Any>(),
                    result = false
                ),
                TestInput(
                    expression = mapOf("in" to listOf("1", 133)),
                    data = emptyMap<String, Any>(),
                    result = false
                ),
                TestInput(
                    expression = mapOf("in" to listOf(1, "133")),
                    data = emptyMap<String, Any>(),
                    result = true
                ),
                TestInput(
                    expression = mapOf("in" to listOf(1, 133)),
                    data = emptyMap<String, Any>(),
                    result = false
                ),
                TestInput(
                    expression = mapOf("in" to listOf("t", true)),
                    data = emptyMap<String, Any>(),
                    result = false
                ),
                TestInput(
                    expression = mapOf("in" to listOf(true, "true gold")),
                    data = emptyMap<String, Any>(),
                    result = true
                ),
                TestInput(
                    expression = mapOf("in" to listOf(null, "banana")),
                    data = emptyMap<String, Any>(),
                    result = false
                ),
                TestInput(
                    expression = mapOf("in" to listOf("n", null)),
                    data = emptyMap<String, Any>(),
                    result = false
                ),
                TestInput(
                    expression = mapOf("in" to listOf(null, listOf(null, null))),
                    data = emptyMap<String, Any>(),
                    result = true
                ),
                TestInput(
                    expression = mapOf("in" to listOf(null, listOf(listOf(null), "banana"))),
                    data = emptyMap<String, Any>(),
                    result = false
                ),
                TestInput(
                    expression = mapOf("in" to listOf(null, listOf("", ""))),
                    data = emptyMap<String, Any>(),
                    result = false
                ),
                TestInput(
                    expression = mapOf("in" to listOf("t", "true")),
                    data = emptyMap<String, Any>(),
                    result = true
                ),
                TestInput(
                    expression = mapOf("in" to listOf("j", "apple", "juice")),
                    data = emptyMap<String, Any>(),
                    result = false
                ),
                TestInput(
                    expression = mapOf("in" to listOf("Spring", "Springfield")),
                    data = emptyMap<String, Any>(),
                    result = true
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
