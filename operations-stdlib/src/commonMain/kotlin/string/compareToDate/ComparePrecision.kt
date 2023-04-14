package string.compareToDate

enum class ComparePrecision{
    MILLISECOND,
    SECOND,
    MINUTE,
    HOUR,
    DAY,
    MONTH,
    YEAR;

    fun position(): Int {
        return position(this)
    }

    fun dateSuffix(): String {
        return dateSuffix(this)
    }

    companion object {
        private const val secondPosition = 19
        private const val minutePosition = 16
        private const val hourPosition = 13
        private const val dayPosition = 10
        private const val monthPosition = 7
        private const val yearPosition = 4
        private const val dateLength = 24

        private const val secondsPrecision = ".000Z"
        private const val minutesPrecision = ":00$secondsPrecision"
        private const val hoursPrecision = ":00$minutesPrecision"

        private const val daysPrecision = "T00$hoursPrecision"
        private const val monthsPrecision = "-01$daysPrecision"
        private const val yearsPrecision = "-01$monthsPrecision"
        fun dateSuffix(precision: ComparePrecision): String {
            return when(precision) {
                SECOND -> secondsPrecision
                MINUTE -> minutesPrecision
                HOUR -> hoursPrecision
                DAY -> daysPrecision
                MONTH -> monthsPrecision
                YEAR -> yearsPrecision
                else -> ""
            }
        }

        fun position(precision: ComparePrecision): Int {
            return when(precision) {
                SECOND -> secondPosition
                MINUTE -> minutePosition
                HOUR -> hourPosition
                DAY -> dayPosition
                MONTH -> monthPosition
                YEAR -> yearPosition
                else -> dateLength
            }
        }
    }
}
