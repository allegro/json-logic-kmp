package string

import JsonLogicResult.Success
import JsonLogicResult.Failure
import JsonLogicEngine
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import string.compareToDate.CompareToDate

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
                result = Success(1)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-03-27", "2023-02-27")
                ),
                result = Success(1)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-03-27", "2022-02-27")
                ),
                result = Success(1)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-03-27", "2023-03-28")
                ),
                result = Success(-1)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-03-27", "2023-05-27")
                ),
                result = Success(-1)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-03-27", "2024-02-27")
                ),
                result = Success(-1)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-03-27", "2023-03-27")
                ),
                result = Success(0)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2012-02-29", "2012-02-28")
                ),
                result = Success(1)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2012-02-28", "2012-02-29")
                ),
                result = Success(-1)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2012-02-29", "2012-02-29")
                ),
                result = Success(0)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-02-29", "2023-02-29")
                ),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-02-31", "2012-02-29")
                ),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2016-02-31", "2012-02-29")
                ),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-05-40", "2012-02-29")
                ),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-01-31", "2012-02-29")
                ),
                result = Success(1)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-04-31", "2012-02-29")
                ),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-01-31", "wrongDateRepresentation")
                ),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("wrongDateRepresentation", "2023-01-31")
                ),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("20222-01-01", "2023-01-31")
                ),
                result = Success(1)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2022-15-01", "2023-01-31")
                ),
                result = Failure.NullResult
            ),
        )
    ) { testInput: TestInput ->
        val evalutionResult = logicEngine.evaluate(testInput.expression, testInput.data)

        evalutionResult shouldBe testInput.result
    }
})
