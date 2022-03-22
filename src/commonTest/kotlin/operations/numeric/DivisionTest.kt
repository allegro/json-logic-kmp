package operations.numeric

import JsonLogicEngineBuilder
import TestInput.Successful
import TestInput.Unsuccessful
import io.kotest.core.spec.style.FunSpec
import testWithFailureResultData
import testWithSuccessResultData

class DivisionTest : FunSpec({
    val logicEngine = JsonLogicEngineBuilder().build()

    context("JsonLogic evaluation with Division operation") {
       testWithSuccessResultData(
            logicEngine,
            listOf(
                Successful(
                    expression = mapOf("/" to listOf(4, 2)),
                    resultValue = 2
                ),
                Successful(
                    expression = mapOf("/" to listOf(2, 4)),
                    resultValue = 0.5
                ),
                Successful(
                    expression = mapOf("/" to listOf("1", 1)),
                    resultValue = 1
                ),
                Successful(
                    expression = mapOf("/" to listOf(0, 1)),
                    resultValue = 0
                ),
                Successful(
                    expression = mapOf("/" to listOf("2.5", "2")),
                    resultValue = 1.25
                ),
                Successful(
                    expression = mapOf("/" to listOf("2.5", "2", "3", 5)),
                    resultValue = 1.25
                ),
                Successful(
                    expression = mapOf("/" to listOf(listOf("2.5"), 2)),
                    resultValue = 1.25
                ),
                Successful(
                    expression = mapOf("/" to listOf(null, 5)),
                    resultValue = 0
                ),
                Successful(
                    expression = mapOf("/" to listOf(false, true)),
                    resultValue = 0
                ),
                Successful(
                    expression = mapOf("/" to listOf(0, true)),
                    resultValue = 0
                ),
                Successful(
                    expression = mapOf("/" to listOf(1, true)),
                    resultValue = 1
                ),
                Successful(
                    expression = mapOf("/" to listOf(listOf("5"), listOf("5"), listOf("5"))),
                    resultValue = 1
                ),
                Successful(
                    expression = mapOf("/" to listOf(listOf("5"), 5)),
                    resultValue = 1
                ),
                Successful(
                    expression = mapOf("/" to listOf(listOf(listOf("5")), 5)),
                    resultValue = 1
                ),
                Successful(
                    expression = mapOf("/" to listOf(listOf(listOf("5")), listOf(5))),
                    resultValue = 1
                ),
                Successful(
                    expression = mapOf("/" to listOf(emptyList<String>(), 2)),
                    resultValue = 0
                )
            )
        )
        testWithFailureResultData(
            logicEngine,
            listOf(
                Unsuccessful(expression = mapOf("/" to listOf("1", "0"))),
                Unsuccessful(expression = mapOf("/" to listOf("1", 0))),
                Unsuccessful(expression = mapOf("/" to listOf("2.5", listOf("2", "3", 5)))),
                Unsuccessful(expression = mapOf("/" to listOf(2, null))),
                Unsuccessful(expression = mapOf("/" to listOf(null, null))),
                Unsuccessful(expression = mapOf("/" to listOf(null))),
                Unsuccessful(expression = mapOf("/" to listOf("banana"))),
                Unsuccessful(expression = mapOf("/" to listOf(true, false))),
                Unsuccessful(expression = mapOf("/" to listOf(true))),
                Unsuccessful(expression = mapOf("/" to listOf(false))),
                Unsuccessful(expression = mapOf("/" to listOf(true, null))),
                Unsuccessful(expression = mapOf("/" to listOf(false, null))),
                Unsuccessful(expression = mapOf("/" to listOf("a", 2))),
                Unsuccessful(expression = mapOf("/" to listOf(listOf(2, "a"), 2))),
                Unsuccessful(expression = mapOf("/" to listOf(listOf("a", 2), 2))),
                Unsuccessful(expression = mapOf("/" to listOf(listOf(2, 2), 2))),
                Unsuccessful(expression = mapOf("/" to listOf(listOf(2, "a"), listOf("a", 2)))),
                Unsuccessful(expression = mapOf("/" to listOf(listOf("5", listOf("5")), listOf("5"), listOf("5")))),
                Unsuccessful(expression = mapOf("/" to listOf(listOf(listOf("5"), listOf(6))))),
            )
        )
    }
})
