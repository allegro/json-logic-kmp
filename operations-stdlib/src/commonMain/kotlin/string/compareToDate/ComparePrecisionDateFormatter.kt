package string.compareToDate

import kotlinx.datetime.Instant

class ComparePrecisionDateFormatter {
    fun formatDate(isoDate: String, precision: ComparePrecision): Instant {
        return Instant.parse(
            isoDate.substring(0, toPosition(precision)) + toDateSuffix(precision)
        )
    }

    private fun toDateSuffix(precision: ComparePrecision): String {
        return when (precision) {
            ComparePrecision.MILLISECOND -> empty
            ComparePrecision.SECOND -> secondsPrecision
            ComparePrecision.MINUTE -> minutesPrecision
            ComparePrecision.HOUR -> hoursPrecision
            ComparePrecision.DAY -> daysPrecision
            ComparePrecision.MONTH -> monthsPrecision
            ComparePrecision.YEAR -> yearsPrecision
        }
    }

    private fun toPosition(precision: ComparePrecision): Int {
        return when (precision) {
            ComparePrecision.MILLISECOND -> dateLength
            ComparePrecision.SECOND -> secondPosition
            ComparePrecision.MINUTE -> minutePosition
            ComparePrecision.HOUR -> hourPosition
            ComparePrecision.DAY -> dayPosition
            ComparePrecision.MONTH -> monthPosition
            ComparePrecision.YEAR -> yearPosition
        }
    }

    companion object {
        private const val secondPosition = 19
        private const val minutePosition = 16
        private const val hourPosition = 13
        private const val dayPosition = 10
        private const val monthPosition = 7
        private const val yearPosition = 4
        private const val dateLength = 24

        private const val empty = ""
        private const val secondsPrecision = ".001Z"
        private const val minutesPrecision = ":00$secondsPrecision"
        private const val hoursPrecision = ":00$minutesPrecision"

        private const val daysPrecision = "T00$hoursPrecision"
        private const val monthsPrecision = "-01$daysPrecision"
        private const val yearsPrecision = "-01$monthsPrecision"
    }
}
