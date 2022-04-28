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
                TestInput(expression = mapOf("!!" to listOf(false)), resultValue = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("!!" to listOf(true)), resultValue = JsonLogicResult.Success(true)),
                TestInput(expression = mapOf("!!" to false), resultValue = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("!!" to "false"), resultValue = JsonLogicResult.Success(true)),
                TestInput(expression = mapOf("!!" to true), resultValue = JsonLogicResult.Success(true)),
                TestInput(expression = mapOf("!!" to 0), resultValue = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("!!" to 1), resultValue = JsonLogicResult.Success(true)),
                TestInput(
                    expression = mapOf("!!" to listOf(emptyList<Boolean>())),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(expression = mapOf("!!" to listOf(0)), resultValue = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("!!" to listOf("")), resultValue = JsonLogicResult.Success(false)),
                TestInput(expression = mapOf("!!" to listOf("0")), resultValue = JsonLogicResult.Success(true)),
                TestInput(expression = mapOf("!!" to listOf(null)), resultValue = JsonLogicResult.Success(false)),
                TestInput(
                    expression = mapOf("!!" to listOf("banana", null)),
                    resultValue = JsonLogicResult.Success(true)
                ),
                TestInput(expression = mapOf("!!" to listOf(13)), resultValue = JsonLogicResult.Success(true)),
                TestInput(
                    expression = mapOf("!!" to listOf(false, false)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(expression = mapOf("!!" to listOf(true, true)), resultValue = JsonLogicResult.Success(true)),
                TestInput(expression = mapOf("!!" to listOf(true, null)), resultValue = JsonLogicResult.Success(true)),
                TestInput(expression = mapOf("!!" to listOf(null, null)), resultValue = JsonLogicResult.Success(false)),
            )
        )
    }
})
