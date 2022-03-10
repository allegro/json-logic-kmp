package operations.string

import LogicOperationTest
import TestInput

class CatTest : LogicOperationTest(
    testName = "JsonLogic evaluation with only Cat operation",
    successResultTestInput = listOf(
        TestInput(
            expression = mapOf("cat" to "ice"),
            resultValue = "ice"
        ),
        TestInput(
            expression = mapOf("cat" to emptyList<String>()),
            resultValue = ""
        ),
        TestInput(
            expression = mapOf("cat" to listOf("ice")),
            resultValue = "ice"
        ),
        TestInput(
            expression = mapOf("cat" to listOf("ice", "cream")),
            resultValue = "icecream"
        ),
        TestInput(
            expression = mapOf("cat" to listOf(1, 2)),
            resultValue = "12"
        ),
        TestInput(
            expression = mapOf("cat" to listOf("Robocop", 2)),
            resultValue = "Robocop2"
        ),
        TestInput(
            expression = mapOf("cat" to listOf("Robocop", 2.0)),
            resultValue = "Robocop2"
        ),
        TestInput(
            expression = mapOf("cat" to listOf(true, "Robocop")),
            resultValue = "trueRobocop"
        ),
        TestInput(
            expression = mapOf("cat" to listOf("false", "Robocop")),
            resultValue = "falseRobocop"
        ),
        TestInput(
            expression = mapOf("cat" to listOf("we all scream for ", "ice", "cream")),
            resultValue = "we all scream for icecream"
        ),
        TestInput(
            expression = mapOf("cat" to listOf("we all scream for ", listOf("ice", "cream"))),
            resultValue = "we all scream for ice,cream"
        ),
        TestInput(
            expression = mapOf("cat" to listOf("easy as ", listOf(1, 2.0, "3"))),
            resultValue = "easy as 1,2,3"
        ),
        TestInput(
            expression = mapOf("cat" to listOf(listOf(2.0))),
            resultValue = "2"
        ),
        TestInput(
            expression = mapOf("cat" to listOf(2.5, 1)),
            resultValue = "2.51"
        ),
        TestInput(
            expression = mapOf("cat" to listOf("2.0")),
            resultValue = "2.0"
        ),
        TestInput(
            expression = mapOf("cat" to listOf("easy as ", listOf(1, 2, "3"))),
            resultValue = "easy as 1,2,3"
        ),
        TestInput(
            expression = mapOf("cat" to listOf("easy as ", listOf(null, listOf(true), "3"))),
            resultValue = "easy as ,true,3"
        ),
        TestInput(
            expression = mapOf("cat" to listOf(emptyList(), listOf(emptyList(), listOf(emptyList<String>())))),
            resultValue = ""
        ),
        TestInput(
            expression = mapOf("cat" to listOf("I love ", mapOf("var" to "filling"), " pie")),
            data = mapOf("filling" to "apple", "temp" to 110),
            resultValue = "I love apple pie"
        ),
    )
)
