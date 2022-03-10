package operations.numeric

import LogicOperationTest
import TestInput

class ModuloTest : LogicOperationTest(
    testName = "JsonLogic evaluation with only Modulo operation",
    successResultTestInput = listOf(
        TestInput(
            expression = mapOf("%" to listOf(1, 2)),
            resultValue = 1
        ),
        TestInput(
            expression = mapOf("%" to listOf(1, 2, 5)),
            resultValue = 1
        ),
        TestInput(
            expression = mapOf("%" to listOf(2, 2)),
            resultValue = 0
        ),
        TestInput(
            expression = mapOf("%" to listOf(3, 2)),
            resultValue = 1
        ),
        TestInput(
            expression = mapOf("%" to listOf("3.5", 1.2)),
            resultValue = 1.1
        ),
        TestInput(
            expression = mapOf("%" to listOf(0, 1.2)),
            resultValue = 0
        ),
        TestInput(
            expression = mapOf("%" to listOf("2", 1.5, "banana")),
            resultValue = 0.5
        ),
        TestInput(
            expression = mapOf("%" to listOf("2", 2.5, listOf("banana"))),
            resultValue = 2
        ),
        TestInput(
            expression = mapOf("%" to listOf(null, 5)),
            resultValue = 0
        ),
        TestInput(
            expression = mapOf("%" to listOf(false, true)),
            resultValue = 0
        ),
        TestInput(
            expression = mapOf("%" to listOf(0, true)),
            resultValue = 0
        ),
        TestInput(
            expression = mapOf("%" to listOf(emptyList<String>(), 2)),
            resultValue = 0
        ),
        TestInput(
            expression = mapOf("%" to listOf(1, true)),
            resultValue = 0
        ),
        TestInput(
            expression = mapOf("%" to listOf(listOf("1"), listOf("2"), listOf("3"))),
            resultValue = 1
        ),

        TestInput(
            expression = mapOf("%" to listOf(listOf("2"), 3)),
            resultValue = 2
        ),
        TestInput(
            expression = mapOf("%" to listOf(listOf(listOf("5")), 6)),
            resultValue = 5
        ),
        TestInput(
            expression = mapOf("%" to listOf(listOf(listOf("5")), listOf(6))),
            resultValue = 5
        )
    ),
    failureResultTestInput = listOf(
        TestInput(
            expression = mapOf("%" to listOf(listOf(listOf("5"), listOf(6)))),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("%" to listOf(listOf("5", listOf("5")), listOf("5"), listOf("5"))),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("%" to listOf(2)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("%" to listOf("a", 2)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("%" to listOf(listOf(2, "a"), 2)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("%" to listOf(listOf("a", 2), 2)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("%" to listOf(listOf(2, 2), 2)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("%" to listOf(listOf(2, "a"), listOf("a", 2))),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("%" to listOf(2, null)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("%" to listOf(null, null)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("%" to listOf(null)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("%" to listOf("banana")),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("%" to listOf(true, false)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("%" to listOf(true)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("%" to listOf(false)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("%" to listOf(true, null)),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("%" to listOf(false, null)),
            resultValue = null
        ),
    )
)

@Suppress("unused")
private val defectiveTestCases = listOf(
    TestInput(
        expression = mapOf("%" to listOf(3.5, 1.3)),
        resultValue = 0.9
    )
)
