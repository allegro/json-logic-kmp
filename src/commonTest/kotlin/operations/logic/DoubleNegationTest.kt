package operations.logic

import LogicOperationTest
import TestInput.Successful

class DoubleNegationTest : LogicOperationTest(
    testName = "JsonLogic evaluation with only DoubleNegation operation",
    successResultTestInput = listOf(
        Successful(expression = mapOf("!!" to listOf(false)), resultValue = false),
        Successful(expression = mapOf("!!" to listOf(true)), resultValue = true),
        Successful(expression = mapOf("!!" to false), resultValue = false),
        Successful(expression = mapOf("!!" to "false"), resultValue = true),
        Successful(expression = mapOf("!!" to true), resultValue = true),
        Successful(expression = mapOf("!!" to 0), resultValue = false),
        Successful(expression = mapOf("!!" to 1), resultValue = true),
        Successful(expression = mapOf("!!" to listOf(emptyList<Boolean>())), resultValue = false),
        Successful(expression = mapOf("!!" to listOf(0)), resultValue = false),
        Successful(expression = mapOf("!!" to listOf("")), resultValue = false),
        Successful(expression = mapOf("!!" to listOf("0")), resultValue = true),
        Successful(expression = mapOf("!!" to listOf(null)), resultValue = false),
        Successful(expression = mapOf("!!" to listOf("banana", null)), resultValue = true),
        Successful(expression = mapOf("!!" to listOf(13)), resultValue = true),
        Successful(expression = mapOf("!!" to listOf(false, false)), resultValue = false),
        Successful(expression = mapOf("!!" to listOf(true, true)), resultValue = true),
        Successful(expression = mapOf("!!" to listOf(true, null)), resultValue = true),
        Successful(expression = mapOf("!!" to listOf(null, null)), resultValue = false),
    )
)
