package operations.numeric

import LogicOperationTest
import TestInput.Successful
import TestInput.Unsuccessful

class MinTest : LogicOperationTest(
    testName = "JsonLogic evaluation with only Min operation",
    successResultTestInput = listOf(
        Successful(
            expression = mapOf("min" to listOf(1, 2, 3)),
            resultValue = 1
        ),
        Successful(
            expression = mapOf("min" to listOf("1", "0.2", 0.3)),
            resultValue = 0.2
        ),
        Successful(
            expression = mapOf("min" to listOf("-2", "0.2", 0.3)),
            resultValue = -2
        ),
        Successful(
            expression = mapOf("min" to listOf(1, 3, 3)),
            resultValue = 1
        ),
        Successful(
            expression = mapOf("min" to listOf(3, 2, 1)),
            resultValue = 1
        ),
        Successful(
            expression = mapOf("min" to listOf(1)),
            resultValue = 1
        ),
        Successful(
            expression = mapOf("min" to listOf("1", 2)),
            resultValue = 1
        ),
    ),
    failureResultTestInput = listOf(
        Unsuccessful(expression = mapOf("min" to listOf(1, "banana"))),
        Unsuccessful(expression = mapOf("min" to listOf(1, "banana", listOf(1, 2)))),
    )
)
