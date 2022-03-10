package operations.numeric

import LogicOperationTest
import TestInput

class DivisionTest : LogicOperationTest(
    testName = "JsonLogic evaluation with only Division operation",
    successResultTestInput = listOf(
        TestInput(
            expression = mapOf("/" to listOf(4, 2)),
            resultValue = 2
        ),
        TestInput(
            expression = mapOf("/" to listOf(2, 4)),
            resultValue = 0.5
        ),
        TestInput(
            expression = mapOf("/" to listOf("1", 1)),
            resultValue = 1
        ),
        TestInput(
            expression = mapOf("/" to listOf(0, 1)),
            resultValue = 0
        ),
        TestInput(
            expression = mapOf("/" to listOf("2.5", "2")),
            resultValue = 1.25
        ),
        TestInput(
            expression = mapOf("/" to listOf("2.5", "2", "3", 5)),
            resultValue = 1.25
        ),
        TestInput(
            expression = mapOf("/" to listOf(listOf("2.5"), 2)),
            resultValue = 1.25
        ),
        TestInput(
            expression = mapOf("/" to listOf(null, 5)),
            resultValue = 0
        ),
        TestInput(
            expression = mapOf("/" to listOf(false, true)),
            resultValue = 0
        ),
        TestInput(
            expression = mapOf("/" to listOf(0, true)),
            resultValue = 0
        ),
        TestInput(
            expression = mapOf("/" to listOf(1, true)),
            resultValue = 1
        ),
        TestInput(
            expression = mapOf("/" to listOf(listOf("5"), listOf("5"), listOf("5"))),
            resultValue = 1
        ),
        TestInput(
            expression = mapOf("/" to listOf(listOf("5"), 5)),
            resultValue = 1
        ),
        TestInput(
            expression = mapOf("/" to listOf(listOf(listOf("5")), 5)),
            resultValue = 1
        ),
        TestInput(
            expression = mapOf("/" to listOf(listOf(listOf("5")), listOf(5))),
            resultValue = 1
        ),
        TestInput(
            expression = mapOf("/" to listOf(emptyList<String>(), 2)),
            resultValue = 0
        )
    ),
    failureResultTestInput = listOf(
        TestInput(
            expression = mapOf("/" to listOf("1", "0")),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("/" to listOf("1", 0)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("/" to listOf("2.5", listOf("2", "3", 5))),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("/" to listOf(2, null)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("/" to listOf(null, null)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("/" to listOf(null)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("/" to listOf("banana")),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("/" to listOf(true, false)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("/" to listOf(true)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("/" to listOf(false)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("/" to listOf(true, null)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("/" to listOf(false, null)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("/" to listOf("a", 2)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("/" to listOf(listOf(2, "a"), 2)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("/" to listOf(listOf("a", 2), 2)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("/" to listOf(listOf(2, 2), 2)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("/" to listOf(listOf(2, "a"), listOf("a", 2))),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("/" to listOf(listOf("5", listOf("5")), listOf("5"), listOf("5"))),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("/" to listOf(listOf(listOf("5"), listOf(6)))),
            resultValue = null
        ),
    )
)
