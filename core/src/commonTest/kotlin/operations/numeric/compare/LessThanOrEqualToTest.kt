package operations.numeric.compare

import TestInput.Successful
import io.kotest.core.spec.style.FunSpec
import testWithSuccessResultData

class LessThanOrEqualToTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with LessThanOrEqualTo operation") {
       testWithSuccessResultData(
            logicEngine,
            listOf(
                Successful(
                    expression = mapOf("<=" to listOf(2, 1)),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf("<=" to listOf(1, 1)),
                    resultValue = true
                ),
                Successful(
                    expression = mapOf("<=" to listOf(1, 2)),
                    resultValue = true
                ),
                Successful(
                    expression = mapOf("<=" to listOf("1", 2)),
                    resultValue = true
                ),
                Successful(
                    expression = mapOf("<=" to listOf(1, 2, 3)),
                    resultValue = true
                ),
                Successful(
                    expression = mapOf("<=" to listOf(1, 2, 3, 0)),
                    resultValue = true
                ),
                Successful(
                    expression = mapOf("<=" to listOf(1, 4, 3)),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf("<=" to listOf(1, 4, 3, 2)),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf("<=" to listOf(4, 1, 3)),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf("<=" to listOf(4, 3, 1)),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf("<=" to listOf("4", 3, "1")),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf("<=" to listOf("banana", 3, "1")),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf("<=" to listOf(4, "3", 1)),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf("<=" to listOf(0, mapOf("var" to "temp"), 100)),
                    data = mapOf("temp" to 37),
                    resultValue = true
                ),
                Successful(
                    expression = mapOf("<=" to listOf("apple", "banana")),
                    resultValue = true
                ),
                Successful(
                    expression = mapOf("<=" to listOf("grapes", "banana")),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf("<=" to listOf("grapes", true)),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf("<=" to listOf(1, false)),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf("<=" to listOf(false, 3, 3)),
                    resultValue = true
                ),
                Successful(
                    expression = mapOf("<=" to listOf("banana", 3, 3)),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf("<=" to listOf(listOf(1, 2, 3), listOf(3))),
                    resultValue = true
                ),
                Successful(
                    expression = mapOf("<=" to listOf("true", true)),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf("<=" to listOf("true", false)),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf("<=" to listOf("true", "false")),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf("<=" to listOf(true, false)),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf("<=" to listOf(true, "false")),
                    resultValue = false
                ),
                Successful(
                    expression = mapOf("<=" to listOf(false, "false")),
                    resultValue = false
                ),
            )
        )
    }
})
