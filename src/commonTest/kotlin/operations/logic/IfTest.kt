package operations.logic

import LogicOperationTest
import TestInput

class IfTest : LogicOperationTest(
    testName = "JsonLogic evaluation with If operation",
    successResultTestInput = listOf(
        TestInput(
            expression = mapOf(
                "if" to listOf(
                    mapOf("missing" to listOf("a", "b")),
                    "Not enough fruit",
                    "OK to proceed"
                )
            ),
            data = mapOf("a" to "apple", "b" to "banana"),
            resultValue = "OK to proceed"
        ),
        TestInput(
            expression = mapOf("if" to listOf(false, "apple", false, "banana", false, "carrot", "date")),
            resultValue = "date"
        ),
        TestInput(
            expression = mapOf("if" to listOf(false, "apple", false, "banana", true, "carrot", "date")),
            resultValue = "carrot"
        ),
        TestInput(
            expression = mapOf("if" to listOf(false, "apple", true, "banana", false, "carrot", "date")),
            resultValue = "banana"
        ),
        TestInput(
            expression = mapOf("if" to listOf(false, "apple", true, "banana", true, "carrot", "date")),
            resultValue = "banana"
        ),
        TestInput(
            expression = mapOf("if" to listOf(true, "apple", false, "banana", false, "carrot", "date")),
            resultValue = "apple"
        ),
        TestInput(
            expression = mapOf("if" to listOf(true, "apple", false, "banana", true, "carrot", "date")),
            resultValue = "apple"
        ),
        TestInput(
            expression = mapOf("if" to listOf(true, "apple", true, "banana", false, "carrot", "date")),
            resultValue = "apple"
        ),
        TestInput(
            expression = mapOf("if" to listOf(true, "apple", true, "banana", true, "carrot", "date")),
            resultValue = "apple"
        ),
        TestInput(
            expression = mapOf("if" to listOf(mapOf("var" to "x"), listOf(mapOf("var" to "y")), 99)),
            data = mapOf("x" to true, "y" to 42),
            resultValue = listOf(42)
        ),
        TestInput(
            expression = mapOf("if" to listOf(true, "apple", true, "banana", "carrot")),
            resultValue = "apple"
        ),
        TestInput(
            expression = mapOf("if" to listOf(true, "apple", false, "banana", "carrot")),
            resultValue = "apple"
        ),
        TestInput(
            expression = mapOf("if" to listOf(false, "apple", true, "banana", "carrot")),
            resultValue = "banana"
        ),
        TestInput(
            expression = mapOf("if" to listOf(false, "apple", false, "banana", "carrot")),
            resultValue = "carrot"
        ),
        TestInput(
            expression = mapOf("if" to listOf(true, "apple", true, "banana")),
            resultValue = "apple"
        ),
        TestInput(
            expression = mapOf("if" to listOf(true, "apple", false, "banana")),
            resultValue = "apple"
        ),
        TestInput(
            expression = mapOf("if" to listOf(false, "apple", true, "banana")),
            resultValue = "banana"
        ),
        TestInput(
            expression = mapOf("if" to listOf(mapOf("missing" to "a"), "missed it", "found it")),
            data = mapOf("a" to "apple"),
            resultValue = "found it"
        ),
        TestInput(
            expression = mapOf("if" to listOf(mapOf("missing" to "a"), "missed it", "found it")),
            data = mapOf("b" to "banana"),
            resultValue = "missed it"
        ),
        TestInput(
            expression = mapOf(
                "if" to listOf(
                    true,
                    mapOf("cat" to listOf("ap", "ple")),
                    mapOf("cat" to listOf("ba", "na", "na"))
                )
            ), resultValue = "apple"
        ),
        TestInput(
            expression = mapOf(
                "if" to listOf(
                    false,
                    mapOf("cat" to listOf("ap", "ple")),
                    mapOf("cat" to listOf("ba", "na", "na"))
                )
            ), resultValue = "banana"
        ),
        TestInput(
            expression = mapOf("if" to listOf(mapOf(">" to listOf(2, 1)), "apple", "banana")),
            resultValue = "apple"
        ),
        TestInput(
            expression = mapOf("if" to listOf(mapOf(">" to listOf(1, 2)), "apple", "banana")),
            resultValue = "banana"
        ),
        TestInput(
            expression = mapOf("if" to listOf(true)),
            resultValue = true
        ),
        TestInput(
            expression = mapOf("if" to listOf(false)),
            resultValue = false
        ),
        TestInput(
            expression = mapOf("if" to listOf("apple")),
            resultValue = "apple"
        ),
        TestInput(
            expression = mapOf("if" to listOf(true, "apple")),
            resultValue = "apple"
        ),
        TestInput(
            expression = mapOf("if" to listOf(emptyList<Any>(), "apple", "banana")),
            resultValue = "banana"
        ),
        TestInput(
            expression = mapOf("if" to listOf(listOf(1), "apple", "banana")),
            resultValue = "apple"
        ),
        TestInput(
            expression = mapOf("if" to listOf(listOf(1, 2, 3, 4), "apple", "banana")),
            resultValue = "apple"
        ),
        TestInput(
            expression = mapOf("if" to listOf("", "apple", "banana")),
            resultValue = "banana"
        ),
        TestInput(
            expression = mapOf("if" to listOf("zucchini", "apple", "banana")),
            resultValue = "apple"
        ),
        TestInput(
            expression = mapOf("if" to listOf("0", "apple", "banana")),
            resultValue = "apple"
        ),
        TestInput(
            expression = mapOf("if" to listOf(mapOf("+" to "0"), "apple", "banana")),
            resultValue = "banana"
        ),
        TestInput(
            expression = mapOf("if" to listOf(mapOf("+" to "1"), "apple", "banana")),
            resultValue = "apple"
        ),
        TestInput(
            expression = mapOf("if" to listOf(0, "apple", "banana")),
            resultValue = "banana"
        ),
        TestInput(
            expression = mapOf("if" to listOf(1, "apple", "banana")),
            resultValue = "apple"
        ),
        TestInput(
            expression = mapOf("if" to listOf(3.1416, "apple", "banana")),
            resultValue = "apple"
        ),
        TestInput(
            expression = mapOf("if" to listOf(-1, "apple", "banana")),
            resultValue = "apple"
        ),
        TestInput(
            expression = mapOf("if" to listOf(true, "yes", "no")),
            resultValue = "yes"
        ),
        TestInput(
            expression = mapOf("if" to listOf(false, "yes", "no")),
            resultValue = "no"
        ),
        TestInput(
            expression = mapOf(
                "if" to listOf(
                    mapOf("<" to listOf(mapOf("var" to "temp"), 0)), "freezing",
                    mapOf("<" to listOf(mapOf("var" to "temp"), 100)), "liquid",
                    "gas"
                )
            ),
            data = mapOf("temp" to 55),
            resultValue = "liquid"
        ),
    ),
    failureResultTestInput = listOf(
        TestInput(
            expression = mapOf("if" to listOf(false, "apple")),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("if" to listOf<Any>()),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("if" to listOf(false, "apple", false, "banana")),
            resultValue = null
        ),
        TestInput(
            expression = mapOf("if" to listOf(false, "apple", false, "banana", false, "carrot")),
            resultValue = null
        )
    )
)
