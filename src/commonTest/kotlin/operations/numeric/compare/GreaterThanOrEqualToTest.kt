package operations.numeric.compare

import JsonLogicEngineBuilder
import TestInput.Successful
import io.kotest.core.spec.style.FunSpec
import testWithSuccessResultData

class GreaterThanOrEqualToTest : FunSpec({
    val logicEngine = JsonLogicEngineBuilder().build()

    context("JsonLogic evaluation with GreaterThanOrEqualTo operation") {
       testWithSuccessResultData(
            logicEngine,
            listOf(
                Successful(
                    expression = mapOf(">=" to listOf(2, 1)),
                    resultValue = true
                ),
                Successful(
                    expression = mapOf(">=" to listOf(1, 1)),
                    resultValue = true
                ),
                Successful(
                    expression = mapOf(">=" to listOf(1, 2)),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf(">=" to listOf("2", 1)),
                    resultValue = true
                ),
                Successful(
                    expression = mapOf(">=" to listOf("2", "banana")),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf(">=" to listOf("apple", "banana")),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf(">=" to listOf("grapes", "banana")),
                    resultValue = true
                ),
                Successful(
                    expression = mapOf(">=" to listOf(1, "banana")),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf(">=" to listOf(1, listOf("banana"))),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf(">=" to listOf(1, 2, 3)),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf(">=" to listOf(1, 2, 3, 4)),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf(">=" to listOf(1, 1, 3)),
                    resultValue = true
                ),
                Successful(
                    expression = mapOf(">=" to listOf(1, 1, 3, 0)),
                    resultValue = true
                ),
                Successful(
                    expression = mapOf(">=" to listOf(1, 4, 3)),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf(">=" to listOf(4, 1, 3)),
                    resultValue = true
                ),
                Successful(
                    expression = mapOf(">=" to listOf(4, 3, 1)),
                    resultValue = true
                ),
                Successful(
                    expression = mapOf(">=" to listOf("4", 3, "1")),
                    resultValue = true
                ),
                Successful(
                    expression = mapOf(">=" to listOf("banana", 3, "1")),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf(">=" to listOf(4, "3", 1)),
                    resultValue = true
                ),
                Successful(
                    expression = mapOf(">=" to listOf(0, mapOf("var" to "temp"), 100)),
                    data = mapOf("temp" to 37),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf(">=" to listOf("grapes", true)),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf(">=" to listOf(1, false)),
                    resultValue = true
                ),
                Successful(
                    expression = mapOf(">=" to listOf(false, 3, 3)),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf(">=" to listOf("banana", 3, 3)),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf(">=" to listOf(listOf(1, 2, 3), listOf(3))),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf(">=" to listOf("true", true)),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf(">=" to listOf("true", false)),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf(">=" to listOf("true", "false")),
                    resultValue = true
                ),
                Successful(
                    expression = mapOf(">=" to listOf(true, false)),
                    resultValue = true
                ),
                Successful(
                    expression = mapOf(">=" to listOf(true, "false")),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf(">=" to listOf(false, "false")),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf(">=" to listOf("banana", "2")),
                    resultValue = true
                ),
                Successful(
                    expression = mapOf(">=" to listOf("banana", 2)),
                    resultValue = false
                ),
            )
        )
    }
})
