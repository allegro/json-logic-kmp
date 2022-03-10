package operations.numeric

import LogicOperationTest
import TestInput

class AdditionTest : LogicOperationTest(
    testName = "JsonLogic evaluation with only Addition operation",
    successResultTestInput = listOf(
        TestInput(
            expression = mapOf("+" to listOf(1, 2)),
            resultValue = 3
        ),
        TestInput(
            expression = mapOf("+" to listOf(listOf("5"), listOf("5"), listOf("5"))),
            resultValue = 15
        ),
        TestInput(
            expression = mapOf("+" to listOf(listOf("5", listOf("5")), listOf("5"), listOf("5"))),
            resultValue = 15
        ),
        TestInput(
            expression = mapOf("+" to listOf(listOf("5"), 6)),
            resultValue = 11
        ),
        TestInput(
            expression = mapOf("+" to listOf(listOf(listOf("5")), 6)),
            resultValue = 11
        ),
        TestInput(
            expression = mapOf("+" to listOf(listOf(listOf("5")), listOf(6))),
            resultValue = 11
        ),
        TestInput(
            expression = mapOf("+" to listOf(listOf(listOf("5"), listOf(6)))),
            resultValue = 5
        ),
        TestInput(
            expression = mapOf("+" to listOf(listOf("5"), listOf("6"))),
            resultValue = 11
        ),
        TestInput(
            expression = mapOf("+" to listOf(2, 2, 2)),
            resultValue = 6
        ),
        TestInput(
            expression = mapOf("+" to listOf(1)),
            resultValue = 1
        ),
        TestInput(
            expression = mapOf("+" to emptyList<Double>()),
            resultValue = 0
        ),
        TestInput(
            expression = mapOf("+" to listOf("1", 1)),
            resultValue = 2
        ),
        TestInput(
            expression = mapOf("+" to listOf("1", 1.5)),
            resultValue = 2.5
        ),
        TestInput(
            expression = mapOf("+" to listOf(12.6543534, 1.1)),
            resultValue = 13.7543534
        ),
        TestInput(
            expression = mapOf("+" to listOf(9, .9, .09, .009, .0009, .00009)),
            resultValue = 9.99999
        ),
        TestInput(
            expression = mapOf("+" to listOf(listOf(2, 2), 2)),
            resultValue = 4
        ),
        TestInput(
            expression = mapOf("+" to listOf(listOf(2, "a"), 2)),
            resultValue = 4
        ),
    ),
    failureResultTestInput = listOf(
        TestInput(
            expression = mapOf("+" to listOf(2, 2, null)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("+" to listOf("1", 1.5, "banana")),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("+" to listOf("1", 1, listOf("banana"))),
            resultValue = null
        ),

        TestInput(
            expression = mapOf("+" to listOf("a", 2)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("+" to listOf(listOf("a", 2), 2)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("+" to listOf(listOf(2, "a"), listOf("a", 2))),
            resultValue = null
        ),

        TestInput(
            expression = mapOf("+" to listOf(null, 5)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("+" to listOf(5, null)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("+" to listOf(null, null)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("+" to listOf(null)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("+" to listOf(true, false)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("+" to listOf(true)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("+" to listOf(false)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("+" to listOf(true, null)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("+" to listOf(false, null)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("+" to listOf(false, true)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("+" to listOf(0, true)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("+" to listOf(1, true)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("+" to listOf(emptyList<String>(), 2)),
            resultValue = null
        ),
    )
)
