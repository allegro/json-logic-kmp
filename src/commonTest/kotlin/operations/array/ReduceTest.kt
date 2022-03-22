package operations.array

import JsonLogicEngineBuilder
import TestInput.Successful
import TestInput.Unsuccessful
import io.kotest.core.spec.style.FunSpec
import testWithFailureResultData
import testWithSuccessResultData

class ReduceTest : FunSpec({
    val logicEngine = JsonLogicEngineBuilder().build()

    context("JsonLogic evaluation with Reduce operation") {
       testWithSuccessResultData(
            logicEngine,
            nameFunction = { "Should apply ${it.data} on ${it.expression.keys} result in ${it.resultValue}" },
            data = listOf(
                Successful(
                    expression = mapOf("reduce" to listOf(mapOf("var" to "integers"), 0)),
                    data = mapOf("integers" to listOf(1, 2, 3, 4)),
                    resultValue = 0
                ),
                Successful(
                    expression = mapOf(
                        "reduce" to listOf(1, 2, 3)
                    ),
                    resultValue = 3
                ),
                Successful(
                    expression = mapOf(
                        "reduce" to listOf(listOf(1, 2), 3, 4)
                    ),
                    resultValue = 3
                ),
                Successful(
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
                    resultValue = 6
                ),
                Successful(
                    expression = mapOf(
                        "reduce" to listOf(
                            mapOf("var" to "integers"),
                            mapOf("*" to listOf(mapOf("var" to "current"), mapOf("var" to "accumulator"))),
                            0
                        )
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3, 4)),
                    resultValue = 0
                ),
                Successful(
                    expression = mapOf(
                        "reduce" to listOf(
                            mapOf("var" to "integers"),
                            mapOf("*" to listOf(mapOf("var" to "current"), mapOf("var" to "accumulator"))),
                            1
                        )
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3, 4)),
                    resultValue = 24
                ),
                Successful(
                    expression = mapOf(
                        "reduce" to listOf(
                            mapOf("var" to "integers"),
                            mapOf("+" to listOf(mapOf("var" to "current"), mapOf("var" to "accumulator"))),
                            0
                        )
                    ),
                    resultValue = 0
                ),
                Successful(
                    expression = mapOf(
                        "reduce" to listOf(
                            mapOf("var" to "integers"),
                            mapOf("+" to listOf(mapOf("var" to "current"), mapOf("var" to "accumulator"))),
                            0
                        )
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3, 4)),
                    resultValue = 10
                ),
            )
        )
        testWithFailureResultData(
            logicEngine,
            listOf(
                Unsuccessful(
                    expression = mapOf(
                        "reduce" to listOf(
                            listOf(1, 5, mapOf("var" to "A")),
                            mapOf("+" to listOf(mapOf("var" to "current"), mapOf("var" to "accumulator"))),
                            9
                        )
                    )
                ),
                Unsuccessful(
                    expression = mapOf(
                        "reduce" to listOf(
                            listOf(1, 5, mapOf("var" to "b")),
                            mapOf("+" to listOf(mapOf("var" to "current"), mapOf("var" to "accumulator"))),
                            9
                        )
                    ),
                    data = mapOf("b" to "banana")
                ),
                Unsuccessful(
                    expression = mapOf(
                        "reduce" to listOf(mapOf("var" to "integers"))
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3, 4))
                ),
                Unsuccessful(
                    expression = mapOf(
                        "reduce" to listOf(
                            mapOf("*" to listOf(mapOf("var" to "current"), mapOf("var" to "accumulator"))),
                            0
                        )
                    ),

                    ),
                Unsuccessful(
                    expression = mapOf("reduce" to listOf(0)),

                    ),
                Unsuccessful(expression = mapOf("reduce" to emptyList<Any>())),
                Unsuccessful(expression = mapOf("reduce" to null)),
                Unsuccessful(
                    expression = mapOf(
                        "reduce" to listOf(listOf(1, 2), null, 4)
                    )
                ),
            )
        )
    }
})
