package operations.numeric

import TestInput.Successful
import TestInput.Unsuccessful
import io.kotest.core.spec.style.FunSpec
import testWithFailureResultData
import testWithInputData

class SubtractionTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with Subtraction operation") {
       testWithInputData(
            logicEngine,
            listOf(
                Successful(
                    expression = mapOf("-" to listOf(2, 3)),
                    resultValue = -1
                ),
                Successful(
                    expression = mapOf("-" to listOf(3, 2)),
                    resultValue = 1
                ),
                Successful(
                    expression = mapOf("-" to listOf(3)),
                    resultValue = -3
                ),
                Successful(
                    expression = mapOf("-" to listOf("1", 1)),
                    resultValue = 0
                ),
                Successful(
                    expression = mapOf("-" to listOf(0, 1)),
                    resultValue = -1
                ),
                Successful(
                    expression = mapOf("-" to listOf("1.3", 0)),
                    resultValue = 1.3
                ),
                Successful(
                    expression = mapOf("-" to listOf("1", 1.5, "banana")),
                    resultValue = -0.5
                ),
                Successful(
                    expression = mapOf("-" to listOf("1", 1, listOf("banana"))),
                    resultValue = 0.0
                ),
                Successful(
                    expression = mapOf("-" to listOf(listOf("5"), listOf("5"), listOf("5"))),
                    resultValue = 0
                ),
                Successful(
                    expression = mapOf("-" to listOf(listOf("5"), 6)),
                    resultValue = -1
                ),
                Successful(
                    expression = mapOf("-" to listOf(listOf(listOf("5")), 6)),
                    resultValue = -1
                ),
                Successful(
                    expression = mapOf("-" to listOf(listOf(listOf("5")), listOf(6))),
                    resultValue = -1
                ),
                Successful(
                    expression = mapOf("-" to listOf(null, 5)),
                    resultValue = -5
                ),
                Successful(
                    expression = mapOf("-" to listOf(2, null)),
                    resultValue = 2
                ),
                Successful(
                    expression = mapOf("-" to listOf(null, null)),
                    resultValue = 0
                ),
                Successful(
                    expression = mapOf("-" to listOf(null)),
                    resultValue = 0
                ),
                Successful(
                    expression = mapOf("-" to listOf(true, false)),
                    resultValue = 1
                ),
                Successful(
                    expression = mapOf("-" to listOf(true)),
                    resultValue = -1
                ),
                Successful(
                    expression = mapOf("-" to listOf(false)),
                    resultValue = 0
                ),
                Successful(
                    expression = mapOf("-" to listOf(true, null)),
                    resultValue = 1
                ),
                Successful(
                    expression = mapOf("-" to listOf(false, null)),
                    resultValue = 0
                ),
                Successful(
                    expression = mapOf("-" to listOf(false, true)),
                    resultValue = -1
                ),
                Successful(
                    expression = mapOf("-" to listOf(0, true)),
                    resultValue = -1
                ),
                Successful(
                    expression = mapOf("-" to listOf(1, true)),
                    resultValue = 0
                ),
                Successful(
                    expression = mapOf("-" to listOf(emptyList<String>(), 2)),
                    resultValue = -2
                ),
            )
        )
        testWithFailureResultData(
            logicEngine,
            listOf(
                Unsuccessful(expression = mapOf("-" to emptyList<Double>())),
                Unsuccessful(expression = mapOf("-" to listOf("a", 2))),
                Unsuccessful(expression = mapOf("-" to listOf(listOf(2, "a"), 2))),
                Unsuccessful(expression = mapOf("-" to listOf(listOf("a", 2), 2))),
                Unsuccessful(expression = mapOf("-" to listOf(listOf(2, 2), 2))),
                Unsuccessful(expression = mapOf("-" to listOf(listOf(2, "a"), listOf("a", 2)))),
                Unsuccessful(expression = mapOf("-" to listOf(listOf("5", listOf("5")), listOf("5"), listOf("5")))),
                Unsuccessful(expression = mapOf("-" to listOf("banana"))),
                Unsuccessful(expression = mapOf("-" to listOf(listOf(listOf("5"), listOf(6))))),
            )
        )
    }
})

@Suppress("unused")
private val defectiveTestCases = listOf(
    Successful(
        expression = mapOf("-" to listOf("2.3", 3.2)),
        resultValue = -0.9
    ),
)
