package operations.string

import LogicOperationTest
import TestInput

class CatTest : LogicOperationTest(
    testName = "JsonLogic evaluation with only Cat operation",
    successResultTestInput = listOf(
        TestInput(
            expression = mapOf("cat" to "ice"),
            data = emptyMap<String, Any>(),
            resultValue = "ice"
        ),
        TestInput(
            expression = mapOf("cat" to emptyList<String>()),
            data = emptyMap<String, Any>(),
            resultValue = ""
        ),
        TestInput(
            expression = mapOf("cat" to listOf("ice")),
            data = emptyMap<String, Any>(),
            resultValue = "ice"
        ),
        TestInput(
            expression = mapOf("cat" to listOf("ice", "cream")),
            data = emptyMap<String, Any>(),
            resultValue = "icecream"
        ),
        TestInput(
            expression = mapOf("cat" to listOf(1, 2)),
            data = emptyMap<String, Any>(),
            resultValue = "12"
        ),
        TestInput(
            expression = mapOf("cat" to listOf("Robocop", 2)),
            data = emptyMap<String, Any>(),
            resultValue = "Robocop2"
        ),
        TestInput(
            expression = mapOf("cat" to listOf("Robocop", 2.0)),
            data = emptyMap<String, Any>(),
            resultValue = "Robocop2"
        ),
        TestInput(
            expression = mapOf("cat" to listOf(true, "Robocop")),
            data = emptyMap<String, Any>(),
            resultValue = "trueRobocop"
        ),
        TestInput(
            expression = mapOf("cat" to listOf("false", "Robocop")),
            data = emptyMap<String, Any>(),
            resultValue = "falseRobocop"
        ),
        TestInput(
            expression = mapOf("cat" to listOf("we all scream for ", "ice", "cream")),
            data = emptyMap<String, Any>(),
            resultValue = "we all scream for icecream"
        ),
        TestInput(
            expression = mapOf("cat" to listOf("we all scream for ", listOf("ice", "cream"))),
            data = emptyMap<String, Any>(),
            resultValue = "we all scream for ice,cream"
        ),
        TestInput(
            expression = mapOf("cat" to listOf("easy as ", listOf(1, 2.0, "3"))),
            data = emptyMap<String, Any>(),
            resultValue = "easy as 1,2,3"
        ),
        TestInput(
            expression = mapOf("cat" to listOf(listOf(2.0))),
            data = emptyMap<String, Any>(),
            resultValue = "2"
        ),
        TestInput(
            expression = mapOf("cat" to listOf(2.5, 1)),
            data = emptyMap<String, Any>(),
            resultValue = "2.51"
        ),
        TestInput(
            expression = mapOf("cat" to listOf("2.0")),
            data = emptyMap<String, Any>(),
            resultValue = "2.0"
        ),
        TestInput(
            expression = mapOf("cat" to listOf("easy as ", listOf(1, 2, "3"))),
            data = emptyMap<String, Any>(),
            resultValue = "easy as 1,2,3"
        ),
        TestInput(
            expression = mapOf("cat" to listOf("easy as ", listOf(null, listOf(true), "3"))),
            data = emptyMap<String, Any>(),
            resultValue = "easy as ,true,3"
        ),
        TestInput(
            expression = mapOf("cat" to listOf(emptyList(), listOf(emptyList(), listOf(emptyList<String>())))),
            data = emptyMap<String, Any>(),
            resultValue = ""
        ),
        TestInput(
            expression = mapOf("cat" to listOf("I love ", mapOf("var" to "filling"), " pie")),
            data = mapOf("filling" to "apple", "temp" to 110),
            resultValue = "I love apple pie"
        ),
    )
)
