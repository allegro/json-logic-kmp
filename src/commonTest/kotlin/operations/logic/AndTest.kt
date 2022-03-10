package operations.logic

import LogicOperationTest
import TestInput

class AndTest : LogicOperationTest(
    testName = "JsonLogic evaluation with And operation",
    successResultTestInput = listOf(
        TestInput(
            expression = mapOf(
                "and" to listOf(
                    mapOf(
                        "<" to listOf(mapOf("var" to "temp"), 110)
                    ),
                    mapOf("==" to listOf(mapOf("var" to "pie.filling"), "apple"))
                )
            ),
            data = mapOf(
                "temp" to 100,
                "pie" to mapOf("filling" to "apple")
            ),
            resultValue = true
        ),
        TestInput(expression = mapOf("and" to listOf(true, false)), resultValue = false),
        TestInput(expression = mapOf("and" to listOf(true, true)), resultValue = true),
        TestInput(expression = mapOf("and" to listOf(false, true)), resultValue = false),
        TestInput(expression = mapOf("and" to listOf(false, false)), resultValue = false),
        TestInput(expression = mapOf("and" to listOf(true, true, true)), resultValue = true),
        TestInput(expression = mapOf("and" to listOf(true, true, false)), resultValue = false),
        TestInput(expression = mapOf("and" to listOf(false)), resultValue = false),
        TestInput(expression = mapOf("and" to listOf(true)), resultValue = true),
        TestInput(expression = mapOf("and" to listOf(1, 3)), resultValue = 3),
        TestInput(expression = mapOf("and" to listOf(3, false)), resultValue = false),
        TestInput(expression = mapOf("and" to listOf(false, 3)), resultValue = false),
        TestInput(
            expression = mapOf("and" to listOf(emptyList<Any>(), true)),
            resultValue = emptyList<Any>()
        ),
        TestInput(expression = mapOf("and" to listOf(0, true)), resultValue = 0),
        TestInput(expression = mapOf("and" to listOf("", true)), resultValue = ""),
        TestInput(expression = mapOf("and" to listOf("0", true)), resultValue = true),
        TestInput(
            expression = mapOf("and" to listOf(mapOf(">" to listOf(3, 1)), true)),
            resultValue = true
        ),
        TestInput(
            expression = mapOf("and" to listOf(mapOf(">" to listOf(3, 1)), false)),
            resultValue = false
        ),
        TestInput(
            expression = mapOf("and" to listOf(mapOf(">" to listOf(3, 1)), mapOf("!" to true))),
            resultValue = false
        ),
        TestInput(
            expression = mapOf("and" to listOf(mapOf(">" to listOf(3, 1)), mapOf("<" to listOf(1, 3)))),
            resultValue = true
        ),
        TestInput(
            expression = mapOf("and" to listOf("0", listOf("banana"))),
            resultValue = listOf("banana")
        ),
        TestInput(
            expression = mapOf("and" to listOf(listOf("grapes"), listOf("banana"))),
            resultValue = listOf("banana")
        ),
        TestInput(expression = mapOf("and" to listOf(false, listOf("banana"))), resultValue = false),
        TestInput(
            expression = mapOf("and" to listOf(true, listOf("banana"))),
            resultValue = listOf("banana")
        ),
        TestInput(
            expression = mapOf("and" to listOf(listOf(null), listOf("banana"))),
            resultValue = listOf("banana")
        ),
        TestInput(expression = mapOf("and" to listOf(listOf(null), true)), resultValue = true),
        TestInput(expression = mapOf("and" to listOf(listOf(null), false)), resultValue = false),
        TestInput(
            expression = mapOf("and" to listOf(listOf(emptyList<String>()), listOf("banana"))),
            resultValue = listOf("banana")
        ),
        TestInput(
            expression = mapOf("and" to listOf(listOf(emptyList<String>()), listOf(null))),
            resultValue = listOf(null)
        ),
        TestInput(
            expression = mapOf("and" to listOf(listOf(emptyList<String>()), listOf(null), listOf("banana"))),
            resultValue = listOf("banana")
        ),
    )
)
