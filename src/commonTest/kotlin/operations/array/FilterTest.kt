package operations.array

import JsonLogicResult
import LogicOperationTest
import TestInput

class FilterTest : LogicOperationTest(
    testName = "JsonLogic evaluation with Filter operation",
    successResultTestInput = listOf(
        TestInput(
            expression = mapOf(
                "filter" to listOf(
                    listOf(1, 2, "banana"),
                    mapOf(">=" to listOf(mapOf("var" to ""), 2))
                )
            ),
            resultValue = listOf(2)
        ),
        TestInput(
            expression = mapOf(
                "filter" to listOf(mapOf(">=" to listOf(mapOf("var" to ""), 2)))
            ),
            data = mapOf("integers" to listOf(1, 2, 3, 4, 5)),
            resultValue = emptyList<Any>()
        ),
        TestInput(
            expression = mapOf("filter" to emptyList<Any>()),
            resultValue = emptyList<Any>()
        ),
        TestInput(
            expression = mapOf("filter" to null),
            resultValue = emptyList<Any>()
        ),
        TestInput(
            expression = mapOf("filter" to "banana"),
            resultValue = emptyList<Any>()
        ),
        TestInput(
            expression = mapOf(
                "filter" to listOf(
                    listOf(1, 2, 3, 4, 5),
                    mapOf(">=" to listOf(mapOf("var" to ""), 2))
                )
            ),
            resultValue = listOf(2, 3, 4, 5)
        ),
        TestInput(
            expression = mapOf(
                "filter" to listOf(
                    listOf(1, 2, 3, 4, 5),
                    listOf(1, 2, 3, 4, 5),
                    mapOf(">=" to listOf(mapOf("var" to ""), 2))
                )
            ),
            resultValue = listOf(1, 2, 3, 4, 5)
        ),
        TestInput(
            expression = mapOf(
                "filter" to listOf(
                    mapOf(">=" to listOf(mapOf("var" to ""), 2)),
                    mapOf(">=" to listOf(mapOf("var" to ""), 2)),
                )
            ),
            resultValue = emptyList<Any>()
        ),
        TestInput(
            expression = mapOf(
                "filter" to listOf(
                    mapOf("var" to "integers"),
                    mapOf(">=" to listOf(mapOf("var" to ""), 2))
                )
            ),
            data = mapOf("integers" to listOf(1, 2, 3, 4, 5)),
            resultValue = listOf(2, 3, 4, 5)
        ),
        TestInput(
            expression = mapOf(
                "filter" to listOf(mapOf("var" to "integers"), false)
            ),
            data = mapOf("integers" to listOf(1, 2, 3)),
            resultValue = emptyList<Any>()
        ),
        TestInput(
            expression = mapOf(
                "filter" to listOf(mapOf("var" to "integers"), true)
            ),
            data = mapOf("integers" to listOf(1, 2, 3)),
            resultValue = listOf(1, 2, 3)
        ),
        TestInput(
            expression = mapOf(
                "filter" to listOf(
                    mapOf("var" to "integers"),
                    mapOf("%" to listOf(mapOf("var" to ""), 2))
                )
            ),
            data = mapOf("integers" to listOf(1, 2, 3, 4, 5)),
            resultValue = listOf(1, 3, 5)
        ),
    )
)
