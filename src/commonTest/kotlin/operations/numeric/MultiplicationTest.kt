package operations.numeric

import LogicOperationTest
import TestInput.Successful
import TestInput.Unsuccessful

class MultiplicationTest : LogicOperationTest(
    testName = "JsonLogic evaluation with only Multiplication operation",
    successResultTestInput = listOf(
        Successful(
            expression = mapOf("*" to listOf(3, 2)),
            resultValue = 6
        ),
        Successful(
            expression = mapOf("*" to listOf(2, 2, 2)),
            resultValue = 8
        ),
        Successful(
            expression = mapOf("*" to listOf(1)),
            resultValue = 1
        ),
        Successful(
            expression = mapOf("*" to listOf("1", 1)),
            resultValue = 1
        ),
        Successful(
            expression = mapOf("*" to listOf("1.7", 3)),
            resultValue = 5.1
        ),
        Successful(
            expression = mapOf("*" to listOf(2, 0)),
            resultValue = 0
        ),
        Successful(
            expression = mapOf("*" to listOf(0, 2)),
            resultValue = 0
        ),
        Successful(
            expression = mapOf("*" to listOf("banana")),
            resultValue = "banana"
        ),
        Successful(
            expression = mapOf("*" to listOf(true)),
            resultValue = true
        ),
        Successful(
            expression = mapOf("*" to listOf(1)),
            resultValue = 1
        ),
        Successful(
            expression = mapOf("*" to listOf(true)),
            resultValue = true
        ),
        Successful(
            expression = mapOf("*" to listOf(false)),
            resultValue = false
        ),

        Successful(
            expression = mapOf("*" to listOf(listOf(2, "a"), 2)),
            resultValue = 4
        ),
        Successful(
            expression = mapOf("*" to listOf(listOf(2, 2), 2)),
            resultValue = 4
        ),
        Successful(
            expression = mapOf("*" to listOf(listOf("5"), listOf("5"), listOf("5"))),
            resultValue = 125
        ),
        Successful(
            expression = mapOf("*" to listOf(listOf("5", listOf("5")), listOf("5"), listOf("5"))),
            resultValue = 125
        ),
        Successful(
            expression = mapOf("*" to listOf(listOf("5"), 6)),
            resultValue = 30
        ),
        Successful(
            expression = mapOf("*" to listOf(listOf(listOf("5")), 6)),
            resultValue = 30
        ),
        Successful(
            expression = mapOf("*" to listOf(listOf(listOf("5")), listOf(6))),
            resultValue = 30
        ),
        Successful(
            expression = mapOf("*" to listOf(listOf(listOf("5"), listOf(6)))),
            resultValue = listOf(listOf("5"), listOf(6))
        ),
    ),
    failureResultTestInput = listOf(
        Unsuccessful(expression = mapOf("*" to listOf(emptyList<String>(), 2))),
        Unsuccessful(expression = mapOf("*" to listOf("2", 1.5, "banana"))),
        Unsuccessful(expression = mapOf("*" to listOf("2", 1.5, listOf("banana")))),
        Unsuccessful(expression = mapOf("*" to listOf(null, 5))),
        Unsuccessful(expression = mapOf("*" to listOf(2, null))),
        Unsuccessful(expression = mapOf("*" to listOf(null, null))),
        Unsuccessful(expression = mapOf("*" to listOf(null))),
        Unsuccessful(expression = mapOf("*" to listOf(listOf("a", 2), 2))),
        Unsuccessful(expression = mapOf("*" to listOf(listOf(2, "a"), listOf("a", 2)))),
        Unsuccessful(expression = mapOf("*" to listOf(true, null))),
        Unsuccessful(expression = mapOf("*" to listOf(false, null))),
        Unsuccessful(expression = mapOf("*" to listOf(false, true))),
        Unsuccessful(expression = mapOf("*" to listOf(0, true))),
        Unsuccessful(expression = mapOf("*" to listOf(1, true))),
        Unsuccessful(expression = mapOf("*" to listOf("a", 2))),
        Unsuccessful(expression = mapOf("*" to listOf(true, false))),
    )
)

@Suppress("unused")
private val defectiveTestCases = listOf(
    Successful(
        expression = mapOf("*" to listOf("1.3", "3.7")),
        resultValue = 4.81
    ),
)
