package operations.logic

import LogicOperationTest
import TestInput

class OrTest : LogicOperationTest(
    testName = "JsonLogic evaluation with only Or operation",
    successResultTestInput = listOf(
        TestInput(expression = mapOf("or" to listOf(true, true)), resultValue = true),
        TestInput(expression = mapOf("or" to listOf(false, true)), resultValue = true),
        TestInput(expression = mapOf("or" to listOf(true, false)), resultValue = true),
        TestInput(expression = mapOf("or" to listOf(false, false)), resultValue = false),
        TestInput(expression = mapOf("or" to listOf(false, false, true)), resultValue = true),
        TestInput(expression = mapOf("or" to listOf(false, false, false)), resultValue = false),
        TestInput(expression = mapOf("or" to listOf(false)), resultValue = false),
        TestInput(expression = mapOf("or" to listOf(true)), resultValue = true),
        TestInput(expression = mapOf("or" to listOf(1, 3)), resultValue = 1),
        TestInput(expression = mapOf("or" to listOf(3, false)), resultValue = 3),
        TestInput(expression = mapOf("or" to listOf(false, 3)), resultValue = 3),
        TestInput(expression = mapOf("or" to listOf(emptyList<String>(), true)), resultValue = true),
        TestInput(expression = mapOf("or" to listOf(0, true)), resultValue = true),
        TestInput(expression = mapOf("or" to listOf("", true)), resultValue = true),
        TestInput(expression = mapOf("or" to listOf("0", true)), resultValue = "0"),
        TestInput(
            expression = mapOf("or" to listOf("0", listOf("banana"))),
            resultValue = "0"
        ),
        TestInput(
            expression = mapOf("or" to listOf(listOf("grapes"), listOf("banana"))),
            resultValue = listOf("grapes")
        ),
        TestInput(
            expression = mapOf("or" to listOf(false, listOf("banana"))),
            resultValue = listOf("banana")
        ),
        TestInput(
            expression = mapOf("or" to listOf(true, listOf("banana"))),
            resultValue = true
        ),
        TestInput(
            expression = mapOf("or" to listOf(listOf(null), listOf("banana"))),
            resultValue = listOf(null)
        ),
        TestInput(expression = mapOf("or" to listOf(listOf(null), true)), resultValue = listOf(null)),
        TestInput(expression = mapOf("or" to listOf(listOf(null), false)), resultValue = listOf(null)),
        TestInput(
            expression = mapOf("or" to listOf(listOf(emptyList<String>()), listOf("banana"))),
            resultValue = listOf(emptyList<String>())
        ),
        TestInput(
            expression = mapOf("or" to listOf(listOf(emptyList<String>()), listOf(null))),
            resultValue = listOf(emptyList<String>())
        ),
        TestInput(
            expression = mapOf("or" to listOf(listOf(emptyList<String>()), listOf(null), listOf("banana"))),
            resultValue = listOf(emptyList<String>())
        ),
    )
)
