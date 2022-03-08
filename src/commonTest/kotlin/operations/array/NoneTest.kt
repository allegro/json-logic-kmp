package operations.array

import JsonLogicEngine
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class NoneTest : FunSpec({
    context("JsonLogic evaluation with None operation") {
        withData(
            nameFn = { "Should apply ${it.data} on ${it.expression} result in ${it.result}" },
            // given
            ts = listOf(
                TestInput(
                    expression = mapOf(
                        "none" to listOf(mapOf("var" to "integers"), mapOf(">=" to listOf(mapOf("var" to ""), 1)))
                    ),
                    data = mapOf("integers" to listOf(1,2,3)),
                    result = false
                ),
                TestInput(
                    expression = mapOf(
                        "none" to listOf(mapOf("var" to "integers"), mapOf("==" to listOf(mapOf("var" to ""), 1)))
                    ),
                    data = mapOf("integers" to listOf(1,2,3)),
                    result = false
                ),
                TestInput(
                    expression = mapOf(
                        "none" to listOf(mapOf("var" to "integers"), mapOf("<" to listOf(mapOf("var" to ""), 1)))
                    ),
                    data = mapOf("integers" to listOf(1,2,3)),
                    result = true
                ),
                TestInput(
                    expression = mapOf(
                        "none" to listOf(mapOf("var" to "integers"), mapOf("<" to listOf(mapOf("var" to ""), 1)))
                    ),
                    data = mapOf("integers" to emptyList<Any>()),
                    result = true
                ),
                TestInput(
                    expression = mapOf(
                        "none" to listOf(mapOf("var" to "items"), mapOf(">=" to listOf(mapOf("var" to "qty"), 1)))
                    ),
                    data = mapOf("items" to listOf(mapOf("qty" to 1, "sku" to "apple"), mapOf("qty" to 2, "sku" to "banana"))),
                    result = false
                ),
                TestInput(
                    expression = mapOf(
                        "none" to listOf(mapOf("var" to "items"), mapOf(">" to listOf(mapOf("var" to "qty"), 1)))
                    ),
                    data = mapOf("items" to listOf(mapOf("qty" to 1, "sku" to "apple"), mapOf("qty" to 2, "sku" to "banana"))),
                    result = false
                ),
                TestInput(
                    expression = mapOf(
                        "none" to listOf(mapOf("var" to "items"), mapOf("<" to listOf(mapOf("var" to "qty"), 1)))
                    ),
                    data = mapOf("items" to listOf(mapOf("qty" to 1, "sku" to "apple"), mapOf("qty" to 2, "sku" to "banana"))),
                    result = true
                ),
                TestInput(
                    expression = mapOf(
                        "none" to listOf(mapOf("var" to "items"), mapOf(">=" to listOf(mapOf("var" to "qty"), 1)))
                    ),
                    data = mapOf("items" to emptyList<Any>()),
                    result = true
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
