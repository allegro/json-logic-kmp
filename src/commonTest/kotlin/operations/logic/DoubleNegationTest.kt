package operations.logic

import LogicOperationTest
import TestInput

class DoubleNegationTest : LogicOperationTest(
    testName = "JsonLogic evaluation with only DoubleNegation operation",
    successResultTestInput = listOf(
        TestInput(expression = mapOf("!!" to listOf(false)), resultValue = false),
        TestInput(expression = mapOf("!!" to listOf(true)), resultValue = true),
        TestInput(expression = mapOf("!!" to false), resultValue = false),
        TestInput(expression = mapOf("!!" to "false"), resultValue = true),
        TestInput(expression = mapOf("!!" to true), resultValue = true),
        TestInput(expression = mapOf("!!" to 0), resultValue = false),
        TestInput(expression = mapOf("!!" to 1), resultValue = true),
        TestInput(expression = mapOf("!!" to listOf(emptyList<Boolean>())), resultValue = false),
        TestInput(expression = mapOf("!!" to listOf(0)), resultValue = false),
        TestInput(expression = mapOf("!!" to listOf("")), resultValue = false),
        TestInput(expression = mapOf("!!" to listOf("0")), resultValue = true),
        TestInput(expression = mapOf("!!" to listOf(null)), resultValue = false),
        TestInput(expression = mapOf("!!" to listOf("banana", null)), resultValue = true),
        TestInput(expression = mapOf("!!" to listOf(13)), resultValue = true),
        TestInput(expression = mapOf("!!" to listOf(false, false)), resultValue = false),
        TestInput(expression = mapOf("!!" to listOf(true, true)), resultValue = true),
        TestInput(expression = mapOf("!!" to listOf(true, null)), resultValue = true),
        TestInput(expression = mapOf("!!" to listOf(null, null)), resultValue = false),
    )
)
