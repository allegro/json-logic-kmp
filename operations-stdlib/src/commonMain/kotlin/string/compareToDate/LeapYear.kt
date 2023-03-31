package string.compareToDate

private const val LAP_YEAR_PERIOD = 4
private const val CENTURY_YEAR_CHECKER = 100
private const val CENTURY_LEAP_YEAR_CHECKER = 400
val Int.isLeapYear: Boolean
    get() = if(mod(LAP_YEAR_PERIOD) == 0 && mod(CENTURY_YEAR_CHECKER) == 0) {
        mod(CENTURY_LEAP_YEAR_CHECKER) == 0
        } else mod(LAP_YEAR_PERIOD) == 0
