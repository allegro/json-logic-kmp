package string

import JsonLogicResult.Failure
import JsonLogicResult.Success
import JsonLogicEngine
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import string.CompareToDate

class CompareToDateTest:FunSpec({
    val operatorName = "compareToDate"
    val logicEngine = JsonLogicEngine.Builder().addStandardOperations(
        mapOf(
            operatorName to CompareToDate
        )
    ).build()
    withData(
        nameFn = { input -> "Should evaluated ${input.expression} with given ${input.data} result in ${input.result}"},
        ts = listOf(
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-03-27", "2023-03-26")
                ),
                result = Success(-1)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-03-27", "2023-02-27")
                ),
                result = Success(-1)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-03-27", "2022-02-27")
                ),
                result = Success(-1)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-03-27", "2023-03-28")
                ),
                result = Success(1)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-03-27", "2023-05-27")
                ),
                result = Success(1)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-03-27", "2024-02-27")
                ),
                result = Success(1)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-03-27", "2023-03-27")
                ),
                result = Success(0)
            ),

        )
    ) { testInput: TestInput ->
        val evalutionResult = logicEngine.evaluate(testInput.expression, testInput.data)

        evalutionResult shouldBe testInput.result
    }
})
