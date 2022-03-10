package operations.numeric

import LogicOperationTest
import TestInput

class SubtractionTest : LogicOperationTest(
    testName = "JsonLogic evaluation with only Subtraction operation",
    successResultTestInput = listOf(
        TestInput(
            expression = mapOf("-" to listOf(2, 3)),
            resultValue = -1
        ),
        TestInput(
            expression = mapOf("-" to listOf(3, 2)),
            resultValue = 1
        ),
        TestInput(
            expression = mapOf("-" to listOf(3)),
            resultValue = -3
        ),

        TestInput(
            expression = mapOf("-" to listOf("1", 1)),
            resultValue = 0
        ),
        TestInput(
            expression = mapOf("-" to listOf(0, 1)),
            resultValue = -1
        ),
        TestInput(
            expression = mapOf("-" to listOf("1.3", 0)),
            resultValue = 1.3
        ),
        TestInput(
            expression = mapOf("-" to listOf("1", 1.5, "banana")),
            resultValue = -0.5
        ),
        TestInput(
            expression = mapOf("-" to listOf("1", 1, listOf("banana"))),
            resultValue = 0.0
        ),
        TestInput(
            expression = mapOf("-" to listOf(listOf("5"), listOf("5"), listOf("5"))),
            resultValue = 0
        ),
        TestInput(
            expression = mapOf("-" to listOf(listOf("5"), 6)),
            resultValue = -1
        ),
        TestInput(
            expression = mapOf("-" to listOf(listOf(listOf("5")), 6)),
            resultValue = -1
        ),
        TestInput(
            expression = mapOf("-" to listOf(listOf(listOf("5")), listOf(6))),
            resultValue = -1
        ),
        TestInput(
            expression = mapOf("-" to listOf(null, 5)),
            resultValue = -5
        ),
        TestInput(
            expression = mapOf("-" to listOf(2, null)),
            resultValue = 2
        ),
        TestInput(
            expression = mapOf("-" to listOf(null, null)),
            resultValue = 0
        ),
        TestInput(
            expression = mapOf("-" to listOf(null)),
            resultValue = 0
        ),
        TestInput(
            expression = mapOf("-" to listOf(true, false)),
            resultValue = 1
        ),
        TestInput(
            expression = mapOf("-" to listOf(true)),
            resultValue = -1
        ),
        TestInput(
            expression = mapOf("-" to listOf(false)),
            resultValue = 0
        ),
        TestInput(
            expression = mapOf("-" to listOf(true, null)),
            resultValue = 1
        ),
        TestInput(
            expression = mapOf("-" to listOf(false, null)),
            resultValue = 0
        ),
        TestInput(
            expression = mapOf("-" to listOf(false, true)),
            resultValue = -1
        ),
        TestInput(
            expression = mapOf("-" to listOf(0, true)),
            resultValue = -1
        ),
        TestInput(
            expression = mapOf("-" to listOf(1, true)),
            resultValue = 0
        ),
        TestInput(
            expression = mapOf("-" to listOf(emptyList<String>(), 2)),
            resultValue = -2
        ),
    ),
    failureResultTestInput = listOf(
        TestInput(
            expression = mapOf("-" to emptyList<Double>()),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("-" to listOf("a", 2)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("-" to listOf(listOf(2, "a"), 2)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("-" to listOf(listOf("a", 2), 2)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("-" to listOf(listOf(2, 2), 2)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("-" to listOf(listOf(2, "a"), listOf("a", 2))),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("-" to listOf(listOf("5", listOf("5")), listOf("5"), listOf("5"))),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("-" to listOf("banana")),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("-" to listOf(listOf(listOf("5"), listOf(6)))),
            resultValue = null
        ),
    )
)

@Suppress("unused")
private val defectiveTestCases = listOf(
    TestInput(
        expression = mapOf("-" to listOf("2.3", 3.2)),
        resultValue = -0.9
    ),
)
