package operations.numeric

import TestInput.Successful
import TestInput.Unsuccessful
import io.kotest.core.spec.style.FunSpec
import testWithFailureResultData
import testWithSuccessResultData

class MaxTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with Max operation") {
       testWithSuccessResultData(
            logicEngine,
            listOf(
                Successful(
                    expression = mapOf("max" to listOf(1, 2, 3)),
                    resultValue = 3
                ),
                Successful(
                    expression = mapOf("max" to listOf(1, 3, 3)),
                    resultValue = 3
                ),
                Successful(
                    expression = mapOf("max" to listOf("-1", -2, "-3")),
                    resultValue = -1
                ),
                Successful(
                    expression = mapOf("max" to listOf(3, 2, 1)),
                    resultValue = 3
                ),
                Successful(
                    expression = mapOf("max" to listOf(1)),
                    resultValue = 1
                ),
                Successful(
                    expression = mapOf("max" to listOf(1, "2")),
                    resultValue = 2
                ),
            )
        )
        testWithFailureResultData(
            logicEngine,
            listOf(
                Unsuccessful(expression = mapOf("max" to listOf(1, "banana"))),
                Unsuccessful(expression = mapOf("max" to listOf(1, "banana", listOf(1, 2)))),
            )
        )
    }
})
