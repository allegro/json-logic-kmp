package operations.array

import JsonLogicEngine
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class ReduceTest : FunSpec({
    context("JsonLogic evaluation with Reduce operation") {
        withData(
            nameFn = { "Should apply ${it.data} on reduce expression result in ${it.result}" },
            // given
            ts = listOf(
                TestInput(
                    expression = mapOf(
                        "reduce" to listOf(mapOf("var" to "integers"))
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3, 4)),
                    result = null
                ),
                TestInput(
                    expression = mapOf("reduce" to listOf(mapOf("var" to "integers"), 0)),
                    data = mapOf("integers" to listOf(1, 2, 3, 4)),
                    result = 0
                ),
                TestInput(
                    expression = mapOf(
                        "reduce" to listOf(
                            mapOf("*" to listOf(mapOf("var" to "current"), mapOf("var" to "accumulator"))),
                            0
                        )
                    ),
                    result = null
                ),
                TestInput(
                    expression = mapOf("reduce" to listOf(0)),
                    result = null
                ),
                TestInput(
                    expression = mapOf("reduce" to emptyList<Any>()),
                    result = null
                ),
                TestInput(
                    expression = mapOf("reduce" to null),
                    result = null
                ),
                TestInput(
                    expression = mapOf(
                        "reduce" to listOf(1, 2, 3)
                    ),
                    result = 3
                ),
                TestInput(
                    expression = mapOf(
                        "reduce" to listOf(listOf(1, 2), 3, 4)
                    ),
                    result = 3
                ),
                TestInput(
                    expression = mapOf(
                        "reduce" to listOf(listOf(1, 2), null, 4)
                    ),
                    result = null
                ),
                TestInput(
                    expression = mapOf(
                        "reduce" to listOf(
                            mapOf("var" to "desserts"),
                            mapOf("+" to listOf(mapOf("var" to "accumulator"), mapOf("var" to "current.qty"))),
                            0
                        )
                    ),
                    data = mapOf(
                        "desserts" to listOf(
                            mapOf("name" to "apple", "qty" to 1),
                            mapOf("name" to "brownie", "qty" to 2),
                            mapOf("name" to "cupcake", "qty" to 3)
                        )
                    ),
                    result = 6
                ),
                TestInput(
                    expression = mapOf(
                        "reduce" to listOf(
                            mapOf("var" to "integers"),
                            mapOf("*" to listOf(mapOf("var" to "current"), mapOf("var" to "accumulator"))),
                            0
                        )
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3, 4)),
                    result = 0
                ),
                TestInput(
                    expression = mapOf(
                        "reduce" to listOf(
                            mapOf("var" to "integers"),
                            mapOf("*" to listOf(mapOf("var" to "current"), mapOf("var" to "accumulator"))),
                            1
                        )
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3, 4)),
                    result = 24
                ),
                TestInput(
                    expression = mapOf(
                        "reduce" to listOf(
                            mapOf("var" to "integers"),
                            mapOf("+" to listOf(mapOf("var" to "current"), mapOf("var" to "accumulator"))),
                            0
                        )
                    ),
                    result = 0
                ),
                TestInput(
                    expression = mapOf(
                        "reduce" to listOf(
                            mapOf("var" to "integers"),
                            mapOf("+" to listOf(mapOf("var" to "current"), mapOf("var" to "accumulator"))),
                            0
                        )
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3, 4)),
                    result = 10
                ),
                TestInput(
                    expression = mapOf(
                        "reduce" to listOf(
                            listOf(1, 5, mapOf("var" to "A")),
                            mapOf("+" to listOf(mapOf("var" to "current"), mapOf("var" to "accumulator"))),
                            9
                        )
                    ),
                    result = null
                ),
                TestInput(
                    expression = mapOf(
                        "reduce" to listOf(
                            listOf(1, 5, mapOf("var" to "b")),
                            mapOf("+" to listOf(mapOf("var" to "current"), mapOf("var" to "accumulator"))),
                            9
                        )
                    ),
                    data = mapOf("b" to "banana"),
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
