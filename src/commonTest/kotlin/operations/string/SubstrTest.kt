package operations.string

import LogicOperationTest
import TestInput

class SubstrTest : LogicOperationTest(
    testName = "JsonLogic evaluation with Substr operation",
    successResultTestInput = listOf(
        TestInput(
            expression = mapOf("substr" to listOf("jsonlogic", 4)),
            data = emptyMap<String, Any>(),
            resultValue = "logic"
        ),
        TestInput(
            expression = mapOf("substr" to listOf("jsonlogic", -5)),
            data = emptyMap<String, Any>(),
            resultValue = "logic"
        ),
        TestInput(
            expression = mapOf("substr" to listOf("jsonlogic", 0, 1)),
            data = emptyMap<String, Any>(),
            resultValue = "j"
        ),
        TestInput(
            expression = mapOf("substr" to listOf("jsonlogic", -1, 1)),
            data = emptyMap<String, Any>(),
            resultValue = "c"
        ),
        TestInput(
            expression = mapOf("substr" to listOf("jsonlogic", 4, 5)),
            data = emptyMap<String, Any>(),
            resultValue = "logic"
        ),
        TestInput(
            expression = mapOf("substr" to listOf("jsonlogic", -5, 5)),
            data = emptyMap<String, Any>(),
            resultValue = "logic"
        ),
        TestInput(
            expression = mapOf("substr" to listOf("jsonlogic", -5, -2)),
            data = emptyMap<String, Any>(),
            resultValue = "log"
        ),
        TestInput(
            expression = mapOf("substr" to listOf("jsonlogic", 1, -5)),
            data = emptyMap<String, Any>(),
            resultValue = "son"
        ),

        TestInput(
            expression = mapOf("substr" to listOf("jsonlogic", 1, -5, 8)),
            data = emptyMap<String, Any>(),
            resultValue = "son"
        ),
        TestInput(
            expression = mapOf("substr" to listOf(true, 1, -5)),
            data = emptyMap<String, Any>(),
            resultValue = ""
        ),
        TestInput(
            expression = mapOf("substr" to listOf(true, 1, 2)),
            data = emptyMap<String, Any>(),
            resultValue = "ru"
        ),
        TestInput(
            expression = mapOf("substr" to listOf(true)),
            data = emptyMap<String, Any>(),
            resultValue = "true"
        ),
        TestInput(
            expression = mapOf("substr" to listOf(listOf("apple", listOf("banana")))),
            data = emptyMap<String, Any>(),
            resultValue = "apple,banana"
        ),
        TestInput(
            expression = mapOf("substr" to listOf(listOf("apple", listOf("banana", true)))),
            data = emptyMap<String, Any>(),
            resultValue = "apple,banana,true"
        ),
        TestInput(
            expression = mapOf("substr" to listOf(listOf("apple", listOf("banana", true, listOf(null))))),
            data = emptyMap<String, Any>(),
            resultValue = "apple,banana,true,"
        ),
        TestInput(
            expression = mapOf("substr" to listOf(listOf("apple", listOf(null, "banana", listOf(null))))),
            data = emptyMap<String, Any>(),
            resultValue = "apple,,banana,"
        ),
        TestInput(
            expression = mapOf("substr" to listOf(133, 1)),
            data = emptyMap<String, Any>(),
            resultValue = "33"
        ),
        TestInput(
            expression = mapOf("substr" to listOf(listOf("apple", "banana"), 1)),
            data = emptyMap<String, Any>(),
            resultValue = "pple,banana"
        ),
        TestInput(
            expression = mapOf("substr" to listOf(emptyList<String>())),
            data = emptyMap<String, Any>(),
            resultValue = ""
        ),
        TestInput(
            expression = mapOf("substr" to listOf("", -3, 15)),
            data = emptyMap<String, Any>(),
            resultValue = ""
        ),
        TestInput(
            expression = mapOf("substr" to listOf(2.0, 1)),
            data = emptyMap<String, Any>(),
            resultValue = ""
        ),
        TestInput(
            expression = mapOf("substr" to listOf(2.5, 1)),
            data = emptyMap<String, Any>(),
            resultValue = ".5"
        ),
        TestInput(
            expression = mapOf("substr" to listOf(2.5)),
            data = emptyMap<String, Any>(),
            resultValue = "2.5"
        ),
        TestInput(
            expression = mapOf("substr" to listOf(2.0)),
            data = emptyMap<String, Any>(),
            resultValue = "2"
        ),
        TestInput(
            expression = mapOf("substr" to listOf("2.0")),
            data = emptyMap<String, Any>(),
            resultValue = "2.0"
        ),
    )
)
