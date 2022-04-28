package operations.numeric.compare

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import testWithInputData

class GreaterThanOrEqualToTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with GreaterThanOrEqualTo operation") {
        testWithInputData(
            logicEngine = logicEngine,
            data = listOf(
                TestInput(
                    expression = mapOf(">=" to listOf(2, 1)),
                    result = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(1, 1)),
                    result = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(1, 2)),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">=" to listOf("2", 1)),
                    result = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(">=" to listOf("2", "banana")),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">=" to listOf("apple", "banana")),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">=" to listOf("grapes", "banana")),
                    result = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(1, "banana")),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(1, listOf("banana"))),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(1, 2, 3)),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(1, 2, 3, 4)),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(1, 1, 3)),
                    result = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(1, 1, 3, 0)),
                    result = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(1, 4, 3)),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(4, 1, 3)),
                    result = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(4, 3, 1)),
                    result = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(">=" to listOf("4", 3, "1")),
                    result = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(">=" to listOf("banana", 3, "1")),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(4, "3", 1)),
                    result = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(0, mapOf("var" to "temp"), 100)),
                    data = mapOf("temp" to 37),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">=" to listOf("grapes", true)),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(1, false)),
                    result = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(false, 3, 3)),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">=" to listOf("banana", 3, 3)),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(listOf(1, 2, 3), listOf(3))),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">=" to listOf("true", true)),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">=" to listOf("true", false)),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">=" to listOf("true", "false")),
                    result = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(true, false)),
                    result = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(true, "false")),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">=" to listOf(false, "false")),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf(">=" to listOf("banana", "2")),
                    result = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf(">=" to listOf("banana", 2)),
                    result = JsonLogicResult.Success(false)
                ),
            )
        )
    }
})
