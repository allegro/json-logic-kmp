package operations.string

import LogicOperationTest
import TestInput

class SubstrTest : LogicOperationTest(
    testName = "JsonLogic evaluation with Substr operation",
    successResultTestInput = listOf(
        TestInput(
            expression = mapOf("substr" to listOf("jsonlogic", 4)),
            resultValue = "logic"
        ),
        TestInput(
            expression = mapOf("substr" to listOf("jsonlogic", -5)),
            resultValue = "logic"
        ),
        TestInput(
            expression = mapOf("substr" to listOf("jsonlogic", 0, 1)),
            resultValue = "j"
        ),
        TestInput(
            expression = mapOf("substr" to listOf("jsonlogic", -1, 1)),
            resultValue = "c"
        ),
        TestInput(
            expression = mapOf("substr" to listOf("jsonlogic", 4, 5)),
            resultValue = "logic"
        ),
        TestInput(
            expression = mapOf("substr" to listOf("jsonlogic", -5, 5)),
            resultValue = "logic"
        ),
        TestInput(
            expression = mapOf("substr" to listOf("jsonlogic", -5, -2)),
            resultValue = "log"
        ),
        TestInput(
            expression = mapOf("substr" to listOf("jsonlogic", 1, -5)),
            resultValue = "son"
        ),

        TestInput(
            expression = mapOf("substr" to listOf("jsonlogic", 1, -5, 8)),
            resultValue = "son"
        ),
        TestInput(
            expression = mapOf("substr" to listOf(true, 1, -5)),
            resultValue = ""
        ),
        TestInput(
            expression = mapOf("substr" to listOf(true, 1, 2)),
            resultValue = "ru"
        ),
        TestInput(
            expression = mapOf("substr" to listOf(true)),
            resultValue = "true"
        ),
        TestInput(
            expression = mapOf("substr" to listOf(listOf("apple", listOf("banana")))),
            resultValue = "apple,banana"
        ),
        TestInput(
            expression = mapOf("substr" to listOf(listOf("apple", listOf("banana", true)))),
            resultValue = "apple,banana,true"
        ),
        TestInput(
            expression = mapOf("substr" to listOf(listOf("apple", listOf("banana", true, listOf(null))))),
            resultValue = "apple,banana,true,"
        ),
        TestInput(
            expression = mapOf("substr" to listOf(listOf("apple", listOf(null, "banana", listOf(null))))),
            resultValue = "apple,,banana,"
        ),
        TestInput(
            expression = mapOf("substr" to listOf(133, 1)),
            resultValue = "33"
        ),
        TestInput(
            expression = mapOf("substr" to listOf(listOf("apple", "banana"), 1)),
            resultValue = "pple,banana"
        ),
        TestInput(
            expression = mapOf("substr" to listOf(emptyList<String>())),
            resultValue = ""
        ),
        TestInput(
            expression = mapOf("substr" to listOf("", -3, 15)),
            resultValue = ""
        ),
        TestInput(
            expression = mapOf("substr" to listOf(2.0, 1)),
            resultValue = ""
        ),
        TestInput(
            expression = mapOf("substr" to listOf(2.5, 1)),
            resultValue = ".5"
        ),
        TestInput(
            expression = mapOf("substr" to listOf(2.5)),
            resultValue = "2.5"
        ),
        TestInput(
            expression = mapOf("substr" to listOf(2.0)),
            resultValue = "2"
        ),
        TestInput(
            expression = mapOf("substr" to listOf("2.0")),
            resultValue = "2.0"
        ),
    )
)
