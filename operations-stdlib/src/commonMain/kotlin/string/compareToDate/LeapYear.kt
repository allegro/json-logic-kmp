package string.compareToDate

val Int.isLeapYear: () -> Boolean
    get() = {
        val lapYearPeriod = 4
        val centuryYearChecker = 100
        val centuryLeapYearChecker = 400
        when {
             this.mod(lapYearPeriod) == 0 -> {
                when {
                    this.mod(centuryYearChecker) == 0 -> this.mod(centuryLeapYearChecker) == 0
                    else -> true
                }
            }
            else -> false
        }
    }
