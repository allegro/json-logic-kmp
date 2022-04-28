package operations.logic

import TestInput.Successful
import io.kotest.core.spec.style.FunSpec
import testWithInputData

class DoubleNegationTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with DoubleNegation operation") {
       testWithInputData(
            logicEngine,
            listOf(
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
    }
})
