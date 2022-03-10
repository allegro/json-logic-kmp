package operations.data

import LogicOperationTest
import TestInput

class VarTest : LogicOperationTest(
    testName = "JsonLogic evaluation with Var operation",
    successResultTestInput = listOf(
        TestInput(
            expression = mapOf(
                "var" to listOf(
                    mapOf(
                        "if" to listOf(
                            mapOf("<" to listOf(mapOf("var" to "temp"), 110)), "pie.filling", "pie.eta"
                        )
                    )
                )
            ),
            data = mapOf("temp" to 100, "pie" to mapOf("filling" to "apple", "eta" to "60s")), resultValue = "apple"
        ),
        TestInput(
            expression = mapOf("var" to emptyList<Any>()),
            data = mapOf("a" to "apple", "b" to "banana"),
            resultValue = mapOf("a" to "apple", "b" to "banana")
        ),
        TestInput(
            expression = mapOf("var" to emptyList<Any>()),
            data = mapOf("a" to "apple", "b" to listOf("banana", "beer")),
            resultValue = mapOf("a" to "apple", "b" to listOf("banana", "beer"))
        ),
        TestInput(expression = mapOf("var" to listOf("a")), data = mapOf("a" to 1), resultValue = 1),
        TestInput(expression = mapOf("var" to "a"), data = mapOf("a" to 1), resultValue = 1),
        TestInput(expression = mapOf("var" to listOf("a", 1)), resultValue = 1),
        TestInput(expression = mapOf("var" to listOf("a", 1, 2)), resultValue = 1),
        TestInput(expression = mapOf("var" to listOf("b", 2)), data = mapOf("a" to 1), resultValue = 2),
        TestInput(expression = mapOf("var" to "a.b"), data = mapOf("a" to mapOf("b" to "c")), resultValue = "c"),
        TestInput(
            expression = mapOf("var" to listOf("a.q", 9)),
            data = mapOf("a" to mapOf("b" to "c")),
            resultValue = 9
        ),
        TestInput(
            expression = mapOf("var" to listOf("a.b", 9)),
            data = mapOf("a" to mapOf("b" to "c")),
            resultValue = "c"
        ),
        TestInput(expression = mapOf("var" to 1), data = listOf("apple", "banana"), resultValue = "banana"),
        TestInput(expression = mapOf("var" to "1"), data = listOf("apple", "banana"), resultValue = "banana"),
        TestInput(
            expression = mapOf("var" to "1.1"),
            data = listOf("apple", listOf("banana", "beer")),
            resultValue = "beer"
        ),

        TestInput(expression = mapOf("var" to ""), data = 1, resultValue = 1),
        TestInput(expression = mapOf("var" to ""), data = listOf(1, 2, 3, 4), resultValue = listOf(1, 2, 3, 4)),
        TestInput(
            expression = mapOf("var" to emptyList<Any>()),
            data = listOf(1, 2, 3, 4),
            resultValue = listOf(1, 2, 3, 4)
        ),
        TestInput(expression = mapOf("var" to null), data = listOf(1, 2, 3, 4), resultValue = listOf(1, 2, 3, 4)),
        TestInput(
            expression = mapOf("var" to listOf(null)),
            data = listOf(1, 2, 3, 4),
            resultValue = listOf(1, 2, 3, 4)
        ),
        TestInput(
            expression = mapOf("var" to listOf("")),
            data = listOf(1, 2, 3, 4),
            resultValue = listOf(1, 2, 3, 4)
        ),
        TestInput(expression = mapOf("var" to listOf(1)), data = listOf(1, 2, 3, 4), resultValue = 2),
        TestInput(expression = mapOf("var" to listOf(listOf(1))), data = listOf(1, 2, 3, 4), resultValue = 2),
        TestInput(expression = mapOf("var" to listOf(listOf(1), 2)), data = listOf(1, 2, 3, 4), resultValue = 2),
        TestInput(expression = mapOf("var" to listOf(1, 2)), data = listOf(1, 2, 3, 4), resultValue = 2),
        TestInput(expression = mapOf("var" to null), data = 1, resultValue = 1),
        TestInput(
            expression = mapOf("var" to null),
            data = mapOf("a" to "apple", "b" to "banana"),
            resultValue = mapOf("a" to "apple", "b" to "banana")
        ),
        TestInput(
            expression = mapOf("var" to null),
            data = mapOf("a" to "apple", "b" to listOf("banana", "beer")),
            resultValue = mapOf("a" to "apple", "b" to listOf("banana", "beer"))
        ),
        TestInput(
            expression = mapOf("var" to null),
            data = listOf("apple", "banana"),
            resultValue = listOf("apple", "banana")
        ),
        TestInput(
            expression = mapOf("var" to null),
            data = listOf("apple", 1, null),
            resultValue = listOf("apple", 1, null)
        ),
        TestInput(
            expression = mapOf("var" to null),
            data = listOf("apple", listOf("banana", "beer")),
            resultValue = listOf("apple", listOf("banana", "beer"))
        ),

        TestInput(expression = mapOf("var" to emptyList<Any>()), data = 1, resultValue = 1),
        TestInput(
            expression = mapOf("var" to emptyList<Any>()),
            data = listOf("apple", "banana"),
            resultValue = listOf("apple", "banana")
        ),
        TestInput(
            expression = mapOf("var" to "1"),
            data = listOf("apple", listOf("banana", "beer")),
            resultValue = listOf("banana", "beer")
        ),
    ),
    failureResultTestInput = listOf(
        TestInput(expression = mapOf("var" to "b"), data = mapOf("a" to 1), resultValue = null),
        TestInput(expression = mapOf("var" to "a"), resultValue = null),
        TestInput(expression = mapOf("var" to listOf(listOf(""))), data = listOf(1, 2, 3, 4), resultValue = null),
        TestInput(expression = mapOf("var" to listOf(listOf(null))), data = listOf(1, 2, 3, 4), resultValue = null),
        TestInput(expression = mapOf("var" to listOf("b")), data = mapOf("a" to 1), resultValue = null),
        TestInput(expression = mapOf("var" to listOf("b")), resultValue = null),
        TestInput(
            expression = mapOf("var" to listOf(emptyList<Any>())),
            data = listOf(1, 2, 3, 4),
            resultValue = null
        ),
        TestInput(expression = mapOf("var" to "a.b.c"), resultValue = null),
        TestInput(expression = mapOf("var" to "a.b.c"), data = mapOf("a" to null), resultValue = null),
        TestInput(expression = mapOf("var" to "a.q"), data = mapOf("a" to mapOf("b" to "c")), resultValue = null),

        TestInput(expression = mapOf("var" to "a.b.c"), data = mapOf("a" to mapOf("b" to null)), resultValue = null),
    )
)
