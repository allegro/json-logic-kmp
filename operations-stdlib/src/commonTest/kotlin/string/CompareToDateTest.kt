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
                    operatorName to listOf("2023-03-27T12:21:21.123Z", "2023-03-27T12:21:21.111Z", "MILLISECOND")
                ),
                result = Success(1)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-03-27T12:21:21.123Z", "2023-03-27T12:21:21.123Z", "MILLISECOND")
                ),
                result = Success(0)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-03-27T12:21:21.123Z", "2023-03-27T12:21:21.124Z", "MILLISECOND")
                ),
                result = Success(-1)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-03-27T12:21:22.123Z", "2023-03-27T12:21:21.111Z", "SECOND")
                ),
                result = Success(1)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-03-27T12:21:21.123Z", "2023-03-27T12:21:21.126Z", "SECOND")
                ),
                result = Success(0)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-03-27T12:21:20.123Z", "2023-03-27T12:21:21.124Z", "SECOND")
                ),
                result = Success(-1)
            ),

            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-03-27T12:22:22.123Z", "2023-03-27T12:21:21.111Z", "MINUTE")
                ),
                result = Success(1)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-03-27T12:21:21.123Z", "2023-03-27T12:21:21.126Z", "MINUTE")
                ),
                result = Success(0)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-03-27T12:20:20.123Z", "2023-03-27T12:21:21.124Z", "MINUTE")
                ),
                result = Success(-1)
            ),

            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-03-27T13:18:22.123Z", "2023-03-27T12:21:21.111Z", "HOUR")
                ),
                result = Success(1)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-03-27T12:35:21.123Z", "2023-03-27T12:19:21.126Z", "HOUR")
                ),
                result = Success(0)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-03-27T11:59:20.123Z", "2023-03-27T12:43:21.124Z", "HOUR")
                ),
                result = Success(-1)
            ),

            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-03-28T12:34:22.123Z", "2023-03-27T12:45:21.111Z", "DAY")
                ),
                result = Success(1)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-03-27T13:21:21.123Z", "2023-03-27T11:21:21.126Z", "DAY")
                ),
                result = Success(0)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-03-26T15:21:20.123Z", "2023-03-27T12:21:21.124Z", "DAY")
                ),
                result = Success(-1)
            ),

            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-04-27T11:21:22.123Z", "2023-03-27T12:45:21.111Z", "MONTH")
                ),
                result = Success(1)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-03-22T11:21:21.123Z", "2023-03-27T12:42:21.126Z", "MONTH")
                ),
                result = Success(0)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-02-22T15:21:20.123Z", "2023-03-27T12:21:21.124Z", "MONTH")
                ),
                result = Success(-1)
            ),

            TestInput(
                expression = mapOf(
                    operatorName to listOf("2024-03-12T12:31:22.123Z", "2023-03-27T12:11:21.111Z", "YEAR")
                ),
                result = Success(1)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-03-23T12:42:21.123Z", "2023-03-27T12:26:21.126Z", "YEAR")
                ),
                result = Success(0)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2022-03-25T12:35:20.123Z", "2023-03-27T12:42:21.124Z", "YEAR")
                ),
                result = Success(-1)
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2022-03-25T12:35:20.123Z", "2023-03-27T12:42:21.124Z", "SOMEOTHER")
                ),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2022-032514", "2023-03-27T12:42:21.124Z", "DAY")
                ),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("2023-03-27T12:42:21.124Z", "2023-0324Z", "DAY")
                ),
                result = Failure.NullResult
            ),

        )
    ) { testInput: TestInput ->
        val evalutionResult = logicEngine.evaluate(testInput.expression, testInput.data)

        evalutionResult shouldBe testInput.result
    }
})
