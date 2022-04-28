package operations.numeric

import TestInput.Successful
import TestInput.Unsuccessful
import io.kotest.core.spec.style.FunSpec
import testWithFailureResultData
import testWithInputData

class ModuloTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with Modulo operation") {
       testWithInputData(
            logicEngine,
            listOf(
                Successful(
                    expression = mapOf("%" to listOf(1, 2)),
                    resultValue = 1
                ),
                Successful(
                    expression = mapOf("%" to listOf(1, 2, 5)),
                    resultValue = 1
                ),
                Successful(
                    expression = mapOf("%" to listOf(2, 2)),
                    resultValue = 0
                ),
                Successful(
                    expression = mapOf("%" to listOf(3, 2)),
                    resultValue = 1
                ),
                Successful(
                    expression = mapOf("%" to listOf("3.5", 1.2)),
                    resultValue = 1.1
                ),
                Successful(
                    expression = mapOf("%" to listOf(0, 1.2)),
                    resultValue = 0
                ),
                Successful(
                    expression = mapOf("%" to listOf("2", 1.5, "banana")),
                    resultValue = 0.5
                ),
                Successful(
                    expression = mapOf("%" to listOf("2", 2.5, listOf("banana"))),
                    resultValue = 2
                ),
                Successful(
                    expression = mapOf("%" to listOf(null, 5)),
                    resultValue = 0
                ),
                Successful(
                    expression = mapOf("%" to listOf(false, true)),
                    resultValue = 0
                ),
                Successful(
                    expression = mapOf("%" to listOf(0, true)),
                    resultValue = 0
                ),
                Successful(
                    expression = mapOf("%" to listOf(emptyList<String>(), 2)),
                    resultValue = 0
                ),
                Successful(
                    expression = mapOf("%" to listOf(1, true)),
                    resultValue = 0
                ),
                Successful(
                    expression = mapOf("%" to listOf(listOf("1"), listOf("2"), listOf("3"))),
                    resultValue = 1
                ),

                Successful(
                    expression = mapOf("%" to listOf(listOf("2"), 3)),
                    resultValue = 2
                ),
                Successful(
                    expression = mapOf("%" to listOf(listOf(listOf("5")), 6)),
                    resultValue = 5
                ),
                Successful(
                    expression = mapOf("%" to listOf(listOf(listOf("5")), listOf(6))),
                    resultValue = 5
                )
            )
        )
        testWithFailureResultData(
            logicEngine,
            listOf(
                Unsuccessful(expression = mapOf("%" to listOf(listOf(listOf("5"), listOf(6))))),
                Unsuccessful(expression = mapOf("%" to listOf(listOf("5", listOf("5")), listOf("5"), listOf("5")))),
                Unsuccessful(expression = mapOf("%" to listOf(2))),
                Unsuccessful(expression = mapOf("%" to listOf("a", 2))),
                Unsuccessful(expression = mapOf("%" to listOf(listOf(2, "a"), 2))),
                Unsuccessful(expression = mapOf("%" to listOf(listOf("a", 2), 2))),
                Unsuccessful(expression = mapOf("%" to listOf(listOf(2, 2), 2))),
                Unsuccessful(expression = mapOf("%" to listOf(listOf(2, "a"), listOf("a", 2)))),
                Unsuccessful(expression = mapOf("%" to listOf(2, null))),
                Unsuccessful(expression = mapOf("%" to listOf(null, null))),
                Unsuccessful(expression = mapOf("%" to listOf(null))),
                Unsuccessful(expression = mapOf("%" to listOf("banana"))),
                Unsuccessful(expression = mapOf("%" to listOf(true, false))),
                Unsuccessful(expression = mapOf("%" to listOf(true))),
                Unsuccessful(expression = mapOf("%" to listOf(false))),
                Unsuccessful(expression = mapOf("%" to listOf(true, null))),
                Unsuccessful(expression = mapOf("%" to listOf(false, null))),
            )
        )
    }
})

@Suppress("unused")
private val defectiveTestCases = listOf(
    Successful(
        expression = mapOf("%" to listOf(3.5, 1.3)),
        resultValue = 0.9
    )
)
