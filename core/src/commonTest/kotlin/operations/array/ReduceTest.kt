package operations.array

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import testWithInputData

class ReduceTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with Reduce operation") {
        testWithInputData(
            logicEngine = logicEngine,
            nameFunction = { "Should apply ${it.data} on ${it.expression.keys} result in ${it.resultValue}" },
            data = listOf(
                TestInput(
                    expression = mapOf(
                        "reduce" to listOf(
                            listOf(1, 5, mapOf("var" to "A")),
                            mapOf("+" to listOf(mapOf("var" to "current"), mapOf("var" to "accumulator"))),
                            9
                        )
                    ),
                    resultValue = JsonLogicResult.NullResultFailure
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
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf(
                        "reduce" to listOf(mapOf("var" to "integers"))
                    ),
                    data = mapOf("integers" to listOf(1, 2, 3, 4)),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf(
                        "reduce" to listOf(
                            mapOf("*" to listOf(mapOf("var" to "current"), mapOf("var" to "accumulator"))),
                            0
                        )
                    ),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf("reduce" to listOf(0)),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf("reduce" to emptyList<Any>()),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf("reduce" to null),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf(
                        "reduce" to listOf(listOf(1, 2), null, 4)
                    ),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf("reduce" to listOf(mapOf("var" to "integers"), 0)),
                    data = mapOf("integers" to listOf(1, 2, 3, 4)),
                    resultValue = JsonLogicResult.Success(0)
                ),
                TestInput(
                    expression = mapOf(
                        "reduce" to listOf(1, 2, 3)
                    ),
                    resultValue = JsonLogicResult.Success(3)
                ),
                TestInput(
                    expression = mapOf(
                        "reduce" to listOf(listOf(1, 2), 3, 4)
                    ),
                    resultValue = JsonLogicResult.Success(3)
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
                    resultValue = JsonLogicResult.Success(6)
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
                    resultValue = JsonLogicResult.Success(0)
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
                    resultValue = JsonLogicResult.Success(24)
                ),
                TestInput(
                    expression = mapOf(
                        "reduce" to listOf(
                            mapOf("var" to "integers"),
                            mapOf("+" to listOf(mapOf("var" to "current"), mapOf("var" to "accumulator"))),
                            0
                        )
                    ),
                    resultValue = JsonLogicResult.Success(0)
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
                    resultValue = JsonLogicResult.Success(10)
                ),
            )
        )
    }
})
