package operations.logic

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import testWithInputData

class DoubleNegationTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with DoubleNegation operation") {
        testWithInputData(
            logicEngine = logicEngine,
            data = listOf(
                TestInput(expression = mapOf("!!" to listOf(false)), result = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("!!" to listOf(true)), result = JsonLogicResult.Success(true)),
                TestInput(expression = mapOf("!!" to false), result = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("!!" to "false"), result = JsonLogicResult.Success(true)),
                TestInput(expression = mapOf("!!" to true), result = JsonLogicResult.Success(true)),
                TestInput(expression = mapOf("!!" to 0), result = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("!!" to 1), result = JsonLogicResult.Success(true)),
                TestInput(
                    expression = mapOf("!!" to listOf(emptyList<Boolean>())),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(expression = mapOf("!!" to listOf(0)), result = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("!!" to listOf("")), result = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("!!" to listOf("0")), result = JsonLogicResult.Success(true)),
                TestInput(expression = mapOf("!!" to listOf(null)), result = JsonLogicResult.Success(false)),
                TestInput(
                    expression = mapOf("!!" to listOf("banana", null)),
                    result = JsonLogicResult.Success(true)
                ),
                TestInput(expression = mapOf("!!" to listOf(13)), result = JsonLogicResult.Success(true)),
                TestInput(
                    expression = mapOf("!!" to listOf(false, false)),
                    result = JsonLogicResult.Success(false)
                ),
                TestInput(expression = mapOf("!!" to listOf(true, true)), result = JsonLogicResult.Success(true)),
                TestInput(expression = mapOf("!!" to listOf(true, null)), result = JsonLogicResult.Success(true)),
                TestInput(expression = mapOf("!!" to listOf(null, null)), result = JsonLogicResult.Success(false)),
            )
        )
    }
})
