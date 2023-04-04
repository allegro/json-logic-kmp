package string.compareToDate

import operation.StandardLogicOperation
import string.StringUnwrapStrategy
import utils.asList

object CompareToDate: StandardLogicOperation, StringUnwrapStrategy {

    private const val DELIMITER = "-"
    private const val DATE_INDEX = 0
    private const val COMPARING_DATE_INDEX = 1

    private const val YEAR_INDEX = 0
    private const val MONTH_INDEX = 1
    private const val DAY_INDEX = 2

    private const val DAY_MIN_VALUE = 1
    private const val MONTH_MIN_VALUE = 1
    private const val MONTH_MAX_VALUE = 12
    private const val YEAR_MAX_LENGTH = 4

    override fun evaluateLogic(expression: Any?, data: Any?): Any? =
        expression.asList.toCompareDateParameters()?.invokeCompare()

    private fun List<Any?>.toCompareDateParameters(): CompareDateArguments? = runCatching {
        val baseDate = (get(DATE_INDEX) as String).splitDate()
        val compareDate = (get(COMPARING_DATE_INDEX) as String).splitDate()

        if (!baseDate.isContainsValidDate() || !compareDate.isContainsValidDate()) {
            return null
        }
        val baseDateParameters = baseDate.toDateParameters() ?: return null
        val compareDateParameters = compareDate.toDateParameters() ?: return null
        CompareDateArguments(
            baseDate = baseDateParameters,
            compareDate = compareDateParameters
        )
    }.getOrNull()

    private fun String.splitDate() = split(DELIMITER)

    private fun List<String>.toDateParameters(): DateParameters? = runCatching {
        DateParameters(
            year = get(YEAR_INDEX).toInt(),
            month = get(MONTH_INDEX).toInt(),
            day = get(DAY_INDEX).toInt()
        )
    }.getOrNull()

    private fun isADayWithinDaysInMonthLimit(year: Int, month: Int, day: Int): Boolean {
        return if(MonthDays.mapIntToMonth(month) == MonthDays.FEBRUARY) {
            day <= MonthDays.februaryDays(year.isLeapYear)
        } else day <= MonthDays.mapIntToMonth(month).standardMax
    }

    private fun List<String>.isContainsValidDate(): Boolean = runCatching {
        val year = get(YEAR_INDEX).toInt()
        val month = get(MONTH_INDEX).toInt()
        val day = get(DAY_INDEX).toInt()

        return get(YEAR_INDEX).length <= YEAR_MAX_LENGTH
            && month <= MONTH_MAX_VALUE
            && month >= MONTH_MIN_VALUE
            && isADayWithinDaysInMonthLimit(year,month,day)
            && day >= DAY_MIN_VALUE
    }.getOrElse { false }

    private fun CompareDateArguments.invokeCompare() = baseDate.compareTo(compareDate)
}
