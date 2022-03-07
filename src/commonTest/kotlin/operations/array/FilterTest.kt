package operations.array

import JsonLogicEngine
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class FilterTest : FunSpec({
    context("JsonLogic evaluation with Filter operation") {
        withData(
            nameFn = { "Should apply ${it.data} on ${it.expression} result in ${it.result}" },
            // given
            ts = listOf(

                TestInput(
                    expression = mapOf(
                        "filter" to listOf(mapOf(">=" to listOf(mapOf("var" to ""), 2)))
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3, 4, 5)),
                    result = emptyList<Any>()
                ),
                TestInput(
                    expression = mapOf("filter" to emptyList<Any>()),
                    result = emptyList<Any>()
                ),
                TestInput(
                    expression = mapOf("filter" to null),
                    result = emptyList<Any>()
                ),
                TestInput(
                    expression = mapOf("filter" to "banana"),
                    result = emptyList<Any>()
                ),
                TestInput(
                    expression = mapOf(
                        "filter" to listOf(
                            listOf(1, 2, 3, 4, 5),
                            mapOf(">=" to listOf(mapOf("var" to ""), 2))
                        )
                    ),
                    result = listOf(2, 3, 4, 5)
                ),
                TestInput(
                    expression = mapOf(
                        "filter" to listOf(
                            listOf(1, 2, 3, 4, 5),
                            listOf(1, 2, 3, 4, 5),
                            mapOf(">=" to listOf(mapOf("var" to ""), 2))
                        )
                    ),
                    result = listOf(1, 2, 3, 4, 5),
                ),
                TestInput(
                    expression = mapOf(
                        "filter" to listOf(
                            mapOf(">=" to listOf(mapOf("var" to ""), 2)),
                            mapOf(">=" to listOf(mapOf("var" to ""), 2)),
                        )
                    ),
                    result = emptyList<Any>()
                ),
                TestInput(
                    expression = mapOf(
                        "filter" to listOf(
                            mapOf("var" to "integers"),
                            mapOf(">=" to listOf(mapOf("var" to ""), 2))
                        )
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3, 4, 5)),
                    result = listOf(2, 3, 4, 5)
                ),
                TestInput(
                    expression = mapOf(
                        "filter" to listOf(mapOf("var" to "integers"), false)
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3)),
                    result = emptyList<Any>()
                ),
                TestInput(
                    expression = mapOf(
                        "filter" to listOf(mapOf("var" to "integers"), true)
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3)),
                    result = listOf(1, 2, 3)
                ),
                TestInput(
                    expression = mapOf(
                        "filter" to listOf(
                            mapOf("var" to "integers"),
                            mapOf("%" to listOf(mapOf("var" to ""), 2))
                        )
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3, 4, 5)),
                    result = listOf(1, 3, 5)
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
