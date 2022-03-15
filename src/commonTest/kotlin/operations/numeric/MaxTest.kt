package operations.numeric

import LogicOperationTest
import TestInput.Successful
import TestInput.Unsuccessful

class MaxTest : LogicOperationTest(
    testName = "JsonLogic evaluation with only Max operation",
    successResultTestInput = listOf(
        Successful(
            expression = mapOf("max" to listOf(1, 2, 3)),
            resultValue = 3
        ),
        Successful(
            expression = mapOf("max" to listOf(1, 3, 3)),
            resultValue = 3
        ),
        Successful(
            expression = mapOf("max" to listOf("-1", -2, "-3")),
            resultValue = -1
        ),
        Successful(
            expression = mapOf("max" to listOf(3, 2, 1)),
            resultValue = 3
        ),
        Successful(
            expression = mapOf("max" to listOf(1)),
            resultValue = 1
        ),
        Successful(
            expression = mapOf("max" to listOf(1, "2")),
            resultValue = 2
        ),
    ),
    failureResultTestInput = listOf(
        Unsuccessful(expression = mapOf("max" to listOf(1, "banana"))),
        Unsuccessful(expression = mapOf("max" to listOf(1, "banana", listOf(1, 2)))),
    )
)
