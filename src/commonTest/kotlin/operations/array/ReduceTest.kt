package operations.array

import LogicOperationTest
import TestInput

class ReduceTest : LogicOperationTest(
    testName = "JsonLogic evaluation with Reduce operation",
    nameFunction = { "Should apply ${it.data} on reduce expression result in ${it.resultValue}" },
    successResultTestInput = listOf(
        TestInput(
            expression = mapOf("reduce" to listOf(mapOf("var" to "integers"), 0)),
            data = mapOf("integers" to listOf(1, 2, 3, 4)),
            resultValue = 0
        ),
        TestInput(
            expression = mapOf(
                "reduce" to listOf(1, 2, 3)
            ),
            resultValue = 3
        ),
        TestInput(
            expression = mapOf(
                "reduce" to listOf(listOf(1, 2), 3, 4)
            ),
            resultValue = 3
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
            resultValue = 6
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
            resultValue = 0
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
            resultValue = 24
        ),
        TestInput(
            expression = mapOf(
                "reduce" to listOf(
                    mapOf("var" to "integers"),
                    mapOf("+" to listOf(mapOf("var" to "current"), mapOf("var" to "accumulator"))),
                    0
                )
            ),
            resultValue = 0
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
            resultValue = 10
        ),
    ),
    failureResultTestInput = listOf(
        TestInput(
            expression = mapOf(
                "reduce" to listOf(
                    listOf(1, 5, mapOf("var" to "A")),
                    mapOf("+" to listOf(mapOf("var" to "current"), mapOf("var" to "accumulator"))),
                    9
                )
            ),
            resultValue = null
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
            resultValue = null
        ),
        TestInput(
            expression = mapOf(
                "reduce" to listOf(mapOf("var" to "integers"))
            ),
            data = mapOf("integers" to listOf(1, 2, 3, 4)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf(
                "reduce" to listOf(
                    mapOf("*" to listOf(mapOf("var" to "current"), mapOf("var" to "accumulator"))),
                    0
                )
            ),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("reduce" to listOf(0)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("reduce" to emptyList<Any>()),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("reduce" to null),
            resultValue = null
        ),
        TestInput(
            expression = mapOf(
                "reduce" to listOf(listOf(1, 2), null, 4)
            ),
            resultValue = null
        ),
    )
)

