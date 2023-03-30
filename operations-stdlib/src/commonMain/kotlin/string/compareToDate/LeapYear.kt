package string.compareToDate

private const val LAP_YEAR_PERIOD = 4
private const val CENTURY_YEAR_CHECKER = 100
private const val CENTURY_LEAP_YEAR_CHECKER = 400
val Int.isLeapYear: () -> Boolean
    get() = {
        when {
             this.mod(LAP_YEAR_PERIOD) == 0 -> {
                when {
                    this.mod(CENTURY_YEAR_CHECKER) == 0 -> this.mod(CENTURY_LEAP_YEAR_CHECKER) == 0
                    else -> true
                }
            }
            else -> false
        }
    }
