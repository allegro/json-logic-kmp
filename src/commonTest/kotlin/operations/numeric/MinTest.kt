package operations.numeric

import JsonLogicEngineBuilder
import TestInput.Successful
import TestInput.Unsuccessful
import io.kotest.core.spec.style.FunSpec
import testWithFailureResultData
import testWithSuccessResultData

class MinTest : FunSpec({
    val logicEngine = JsonLogicEngineBuilder().build()

    context("JsonLogic evaluation with Min operation") {
       testWithSuccessResultData(
            logicEngine,
            listOf(
                Successful(
                    expression = mapOf("min" to listOf(1, 2, 3)),
                    resultValue = 1
                ),
                Successful(
                    expression = mapOf("min" to listOf("1", "0.2", 0.3)),
                    resultValue = 0.2
                ),
                Successful(
                    expression = mapOf("min" to listOf("-2", "0.2", 0.3)),
                    resultValue = -2
                ),
                Successful(
                    expression = mapOf("min" to listOf(1, 3, 3)),
                    resultValue = 1
                ),
                Successful(
                    expression = mapOf("min" to listOf(3, 2, 1)),
                    resultValue = 1
                ),
                Successful(
                    expression = mapOf("min" to listOf(1)),
                    resultValue = 1
                ),
                Successful(
                    expression = mapOf("min" to listOf("1", 2)),
                    resultValue = 1
                ),
            )
        )
        testWithFailureResultData(
            logicEngine,
            listOf(
                Unsuccessful(expression = mapOf("min" to listOf(1, "banana"))),
                Unsuccessful(expression = mapOf("min" to listOf(1, "banana", listOf(1, 2)))),
            )
        )
    }
})
