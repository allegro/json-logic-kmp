package operations.logic

import LogicOperationTest
import TestInput.Successful

class NegationTest : LogicOperationTest(
    testName = "JsonLogic evaluation with only Negation operation",
    successResultTestInput = listOf(
        Successful(expression = mapOf("!" to listOf(false)), resultValue = true),
        Successful(expression = mapOf("!" to listOf(true)), resultValue = false),
        Successful(expression = mapOf("!" to "false"), resultValue = false),
        Successful(expression = mapOf("!" to false), resultValue = true),
        Successful(expression = mapOf("!" to true), resultValue = false),
        Successful(expression = mapOf("!" to 0), resultValue = true),
        Successful(expression = mapOf("!" to 1), resultValue = false),
        Successful(expression = mapOf("!" to listOf(emptyList<Boolean>())), resultValue = true),
        Successful(expression = mapOf("!" to listOf(0)), resultValue = true),
        Successful(expression = mapOf("!" to listOf("")), resultValue = true),
        Successful(expression = mapOf("!" to listOf("0")), resultValue = false),
        Successful(expression = mapOf("!" to listOf(null)), resultValue = true),
        Successful(expression = mapOf("!" to listOf("banana", null)), resultValue = false),
        Successful(expression = mapOf("!" to listOf(13)), resultValue = false),
        Successful(expression = mapOf("!" to listOf(false, false)), resultValue = true),
        Successful(expression = mapOf("!" to listOf(true, true)), resultValue = false),
        Successful(expression = mapOf("!" to listOf(true, null)), resultValue = false),
        Successful(expression = mapOf("!" to listOf(null, null)), resultValue = true),
    )
)
