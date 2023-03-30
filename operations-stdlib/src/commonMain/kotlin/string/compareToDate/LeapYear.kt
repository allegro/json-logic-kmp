package string.compareToDate

val Int.isLeapYear: () -> Boolean
    get() = {
        when {
             this.mod(4) == 0 -> {
                when {
                    this.mod(100) == 0 -> this.mod(400) == 0
                    else -> true
                }
            }
            else -> false
        }
    }
