package operations.numeric

import LogicOperationTest
import TestInput

class MaxTest : LogicOperationTest(
    testName = "JsonLogic evaluation with only Max operation",
    successResultTestInput = listOf(
        TestInput(
            expression = mapOf("max" to listOf(1, 2, 3)),
            resultValue = 3
        ),
        TestInput(
            expression = mapOf("max" to listOf(1, 3, 3)),
            resultValue = 3
        ),
        TestInput(
            expression = mapOf("max" to listOf("-1", -2, "-3")),
            resultValue = -1
        ),
        TestInput(
            expression = mapOf("max" to listOf(3, 2, 1)),
            resultValue = 3
        ),
        TestInput(
            expression = mapOf("max" to listOf(1)),
            resultValue = 1
        ),
        TestInput(
            expression = mapOf("max" to listOf(1, "2")),
            resultValue = 2
        ),
    ),
    failureResultTestInput = listOf(
        TestInput(
            expression = mapOf("max" to listOf(1, "banana")),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("max" to listOf(1, "banana", listOf(1, 2))),
            resultValue = null
        ),
    )
)
