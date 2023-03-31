package string.compareToDate

enum class MonthDays(val standardMax: Int) {
    JANUARY(31),
    FEBRUARY(28),
    MARCH(31),
    APRIL(30),
    MAY(31),
    JUNE(30),
    JULY(31),
    AUGUST(31),
    SEPTEMBER(30),
    OCTOBER(31),
    NOVEMBER(30),
    DECEMBER(31);

    companion object {
        private const val FEBRUARY_LEAP_YEAR_MAX_DAYS = 29
        private val map = MonthDays.values().associateBy(MonthDays::ordinal)
        fun mapIntToMonth(type: Int) = map[type-1] ?: throw IllegalArgumentException()

        fun februaryDays(isLeapYear: Boolean): Int {
            return if (isLeapYear) { FEBRUARY_LEAP_YEAR_MAX_DAYS } else FEBRUARY.standardMax
        }
    }
}
