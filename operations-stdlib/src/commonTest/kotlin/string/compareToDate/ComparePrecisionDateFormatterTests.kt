package string.compareToDate

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class ComparePrecisionDateFormatterTests : FunSpec({
    val sut = ComparePrecisionDateFormatter()
    withData(
        nameFn = { input ->
            "Should evaluated ${input.precision} with given ${input.precision} result in ${input.result}"
        }, ts = listOf(
            ComparePrecisionDateFormatterTestInput(
                "2023-04-18T12:34:56.123Z", ComparePrecision.MILLISECOND, "2023-04-18T12:34:56.123Z"
            ),
            ComparePrecisionDateFormatterTestInput(
                "2023-04-18T12:34:56.123Z", ComparePrecision.SECOND, "2023-04-18T12:34:56.001Z"
            ),
            ComparePrecisionDateFormatterTestInput(
                "2023-04-18T12:34:56.123Z", ComparePrecision.MINUTE, "2023-04-18T12:34:00.001Z"
            ),
            ComparePrecisionDateFormatterTestInput(
                "2023-04-18T12:34:56.123Z", ComparePrecision.HOUR, "2023-04-18T12:00:00.001Z"
            ),
            ComparePrecisionDateFormatterTestInput(
                "2023-04-18T12:34:56.123Z", ComparePrecision.DAY, "2023-04-18T00:00:00.001Z"
            ),
            ComparePrecisionDateFormatterTestInput(
                "2023-04-18T12:34:56.123Z", ComparePrecision.MONTH, "2023-04-01T00:00:00.001Z"
            ),
            ComparePrecisionDateFormatterTestInput(
                "2023-04-18T12:34:56.123Z", ComparePrecision.YEAR, "2023-01-01T00:00:00.001Z"
            ),
        )

    ) { testInput: ComparePrecisionDateFormatterTestInput ->
        val result = sut.formatDate(testInput.baseDate, testInput.precision)
        result.toString() shouldBe testInput.result
    }
})

private class ComparePrecisionDateFormatterTestInput(
    val baseDate: String, val precision: ComparePrecision, val result: String
)
