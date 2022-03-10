package operations.logic

import LogicOperationTest
import TestInput

class NegationTest : LogicOperationTest(
    testName = "JsonLogic evaluation with only Negation operation",
    successResultTestInput = listOf(
        TestInput(expression = mapOf("!" to listOf(false)), resultValue = true),
        TestInput(expression = mapOf("!" to listOf(true)), resultValue = false),
        TestInput(expression = mapOf("!" to "false"), resultValue = false),
        TestInput(expression = mapOf("!" to false), resultValue = true),
        TestInput(expression = mapOf("!" to true), resultValue = false),
        TestInput(expression = mapOf("!" to 0), resultValue = true),
        TestInput(expression = mapOf("!" to 1), resultValue = false),
        TestInput(expression = mapOf("!" to listOf(emptyList<Boolean>())), resultValue = true),
        TestInput(expression = mapOf("!" to listOf(0)), resultValue = true),
        TestInput(expression = mapOf("!" to listOf("")), resultValue = true),
        TestInput(expression = mapOf("!" to listOf("0")), resultValue = false),
        TestInput(expression = mapOf("!" to listOf(null)), resultValue = true),
        TestInput(expression = mapOf("!" to listOf("banana", null)), resultValue = false),
        TestInput(expression = mapOf("!" to listOf(13)), resultValue = false),
        TestInput(expression = mapOf("!" to listOf(false, false)), resultValue = true),
        TestInput(expression = mapOf("!" to listOf(true, true)), resultValue = false),
        TestInput(expression = mapOf("!" to listOf(true, null)), resultValue = false),
        TestInput(expression = mapOf("!" to listOf(null, null)), resultValue = true),
    )
)
