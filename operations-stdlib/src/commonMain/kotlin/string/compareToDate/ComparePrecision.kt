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
        return when(this) {
            SECOND -> 19
            MINUTE -> 16
            HOUR -> 13
            DAY -> 10
            MONTH -> 7
            YEAR -> 4
            else -> 24
        }
    }

    fun dateSuffix(): String {
        return dateSuffix(this)
    }

    companion object {
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
    }
}
