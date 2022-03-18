package operations.numeric

import TestInput.Successful
import TestInput.Unsuccessful
import io.kotest.core.spec.style.FunSpec
import testWithFailureResultData
import testWithSuccessResultData

class AdditionTest : FunSpec({
    context("JsonLogic evaluation with Addition operation") {
        testWithSuccessResultData(
            listOf(
                Successful(
                    expression = mapOf("+" to listOf(1, 2)),
                    resultValue = 3
                ),
                Successful(
                    expression = mapOf("+" to listOf(listOf("5"), listOf("5"), listOf("5"))),
                    resultValue = 15
                ),
                Successful(
                    expression = mapOf("+" to listOf(listOf("5", listOf("5")), listOf("5"), listOf("5"))),
                    resultValue = 15
                ),
                Successful(
                    expression = mapOf("+" to listOf(listOf("5"), 6)),
                    resultValue = 11
                ),
                Successful(
                    expression = mapOf("+" to listOf(listOf(listOf("5")), 6)),
                    resultValue = 11
                ),
                Successful(
                    expression = mapOf("+" to listOf(listOf(listOf("5")), listOf(6))),
                    resultValue = 11
                ),
                Successful(
                    expression = mapOf("+" to listOf(listOf(listOf("5"), listOf(6)))),
                    resultValue = 5
                ),
                Successful(
                    expression = mapOf("+" to listOf(listOf("5"), listOf("6"))),
                    resultValue = 11
                ),
                Successful(
                    expression = mapOf("+" to listOf(2, 2, 2)),
                    resultValue = 6
                ),
                Successful(
                    expression = mapOf("+" to listOf(1)),
                    resultValue = 1
                ),
                Successful(
                    expression = mapOf("+" to emptyList<Double>()),
                    resultValue = 0
                ),
                Successful(
                    expression = mapOf("+" to listOf("1", 1)),
                    resultValue = 2
                ),
                Successful(
                    expression = mapOf("+" to listOf("1", 1.5)),
                    resultValue = 2.5
                ),
                Successful(
                    expression = mapOf("+" to listOf(12.6543534, 1.1)),
                    resultValue = 13.7543534
                ),
                Successful(
                    expression = mapOf("+" to listOf(9, .9, .09, .009, .0009, .00009)),
                    resultValue = 9.99999
                ),
                Successful(
                    expression = mapOf("+" to listOf(listOf(2, 2), 2)),
                    resultValue = 4
                ),
                Successful(
                    expression = mapOf("+" to listOf(listOf(2, "a"), 2)),
                    resultValue = 4
                ),
            )
        )
        testWithFailureResultData(
            listOf(
                Unsuccessful(expression = mapOf("+" to listOf(2, 2, null))),
                Unsuccessful(expression = mapOf("+" to listOf("1", 1.5, "banana"))),
                Unsuccessful(expression = mapOf("+" to listOf("1", 1, listOf("banana")))),
                Unsuccessful(expression = mapOf("+" to listOf("a", 2))),
                Unsuccessful(expression = mapOf("+" to listOf(listOf("a", 2), 2))),
                Unsuccessful(expression = mapOf("+" to listOf(listOf(2, "a"), listOf("a", 2)))),
                Unsuccessful(expression = mapOf("+" to listOf(null, 5))),
                Unsuccessful(expression = mapOf("+" to listOf(5, null))),
                Unsuccessful(expression = mapOf("+" to listOf(null, null))),
                Unsuccessful(expression = mapOf("+" to listOf(null))),
                Unsuccessful(expression = mapOf("+" to listOf(true, false))),
                Unsuccessful(expression = mapOf("+" to listOf(true))),
                Unsuccessful(expression = mapOf("+" to listOf(false))),
                Unsuccessful(expression = mapOf("+" to listOf(true, null))),
                Unsuccessful(expression = mapOf("+" to listOf(false, null))),
                Unsuccessful(expression = mapOf("+" to listOf(false, true))),
                Unsuccessful(expression = mapOf("+" to listOf(0, true))),
                Unsuccessful(expression = mapOf("+" to listOf(1, true))),
                Unsuccessful(expression = mapOf("+" to listOf(emptyList<String>(), 2))),
            )
        )
    }
})
