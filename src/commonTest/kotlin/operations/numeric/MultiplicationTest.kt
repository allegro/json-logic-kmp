package operations.numeric

import LogicOperationTest
import TestInput

class MultiplicationTest : LogicOperationTest(
    testName = "JsonLogic evaluation with only Multiplication operation",
    successResultTestInput = listOf(

        TestInput(
            expression = mapOf("*" to listOf(3, 2)),
            resultValue = 6
        ),
        TestInput(
            expression = mapOf("*" to listOf(2, 2, 2)),
            resultValue = 8
        ),
        TestInput(
            expression = mapOf("*" to listOf(1)),
            resultValue = 1
        ),
        TestInput(
            expression = mapOf("*" to listOf("1", 1)),
            resultValue = 1
        ),
        TestInput(
            expression = mapOf("*" to listOf("1.7", 3)),
            resultValue = 5.1
        ),
        TestInput(
            expression = mapOf("*" to listOf(2, 0)),
            resultValue = 0
        ),
        TestInput(
            expression = mapOf("*" to listOf(0, 2)),
            resultValue = 0
        ),
        TestInput(
            expression = mapOf("*" to listOf("banana")),
            resultValue = "banana"
        ),
        TestInput(
            expression = mapOf("*" to listOf(true)),
            resultValue = true
        ),
        TestInput(
            expression = mapOf("*" to listOf(1)),
            resultValue = 1
        ),
        TestInput(
            expression = mapOf("*" to listOf(true)),
            resultValue = true
        ),
        TestInput(
            expression = mapOf("*" to listOf(false)),
            resultValue = false
        ),

        TestInput(
            expression = mapOf("*" to listOf(listOf(2, "a"), 2)),
            resultValue = 4
        ),

        TestInput(
            expression = mapOf("*" to listOf(listOf(2, 2), 2)),
            resultValue = 4
        ),
        TestInput(
            expression = mapOf("*" to listOf(listOf("5"), listOf("5"), listOf("5"))),
            resultValue = 125
        ),
        TestInput(
            expression = mapOf("*" to listOf(listOf("5", listOf("5")), listOf("5"), listOf("5"))),
            resultValue = 125
        ),
        TestInput(
            expression = mapOf("*" to listOf(listOf("5"), 6)),
            resultValue = 30
        ),
        TestInput(
            expression = mapOf("*" to listOf(listOf(listOf("5")), 6)),
            resultValue = 30
        ),
        TestInput(
            expression = mapOf("*" to listOf(listOf(listOf("5")), listOf(6))),
            resultValue = 30
        ),
        TestInput(
            expression = mapOf("*" to listOf(listOf(listOf("5"), listOf(6)))),
            resultValue = listOf(listOf("5"), listOf(6))
        ),
    ),
    failureResultTestInput = listOf(
        TestInput(
            expression = mapOf("*" to listOf(emptyList<String>(), 2)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("*" to listOf("2", 1.5, "banana")),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("*" to listOf("2", 1.5, listOf("banana"))),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("*" to listOf(null, 5)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("*" to listOf(2, null)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("*" to listOf(null, null)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("*" to listOf(null)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("*" to listOf(listOf("a", 2), 2)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("*" to listOf(listOf(2, "a"), listOf("a", 2))),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("*" to listOf(true, null)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("*" to listOf(false, null)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("*" to listOf(false, true)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("*" to listOf(0, true)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("*" to listOf(1, true)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("*" to listOf("a", 2)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("*" to listOf(true, false)),
            resultValue = null
        ),
    )
)

@Suppress("unused")
private val defectiveTestCases = listOf(
    TestInput(
        expression = mapOf("*" to listOf("1.3", "3.7")),
        data = emptyMap<String, Any>(),
        resultValue = 4.81
    ),
)
