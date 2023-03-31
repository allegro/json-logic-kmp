package string.compareToDate

private const val TWENTY_EIGHT_DAYS = 28
private const val TWENTY_NINE_DAYS = 29
private const val THIRTY_DAYS = 30
private const val THIRTY_ONE_DAYS = 31
enum class MonthDays(val standardMax: Int) {
    JANUARY(THIRTY_ONE_DAYS),
    FEBRUARY(TWENTY_EIGHT_DAYS),
    MARCH(THIRTY_ONE_DAYS),
    APRIL(THIRTY_DAYS),
    MAY(THIRTY_ONE_DAYS),
    JUNE(THIRTY_DAYS),
    JULY(THIRTY_ONE_DAYS),
    AUGUST(THIRTY_ONE_DAYS),
    SEPTEMBER(THIRTY_DAYS),
    OCTOBER(THIRTY_ONE_DAYS),
    NOVEMBER(THIRTY_DAYS),
    DECEMBER(THIRTY_ONE_DAYS);

    companion object {
        private val map = MonthDays.values().associateBy(MonthDays::ordinal)
        fun mapIntToMonth(type: Int) = map[type-1] ?: throw IllegalArgumentException()

        fun februaryDays(isLeapYear: Boolean): Int {
            return if (isLeapYear) { TWENTY_NINE_DAYS } else FEBRUARY.standardMax
        }
    }
}
