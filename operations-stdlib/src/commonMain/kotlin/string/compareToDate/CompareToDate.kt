package string.compareToDate

import operation.StandardLogicOperation
import string.StringUnwrapStrategy
import utils.asList

object CompareToDate: StandardLogicOperation, StringUnwrapStrategy {

    private const val YEAR_LENGTH = 4

    private const val DELIMITER = "-"
    private const val DATE_INDEX = 0
    private const val COMPARING_DATE_INDEX = 1

    private const val YEAR_INDEX = 0
    private const val MONTH_INDEX = 1
    private const val DAY_INDEX = 2
    private const val FEBRUARY_MONTH_INDEX = 2
    private val MONTHS_WITH_31_DAYS = intArrayOf(1,3,5,7,8,10,12)

    private const val DAY_30_DAYS_VALUE = 30
    private const val DAY_MAX_VALUE = 31
    private const val DAY_FEBRUARY_LEAP_YEAR_MAX_VALUE = 29
    private const val DAY_FEBRUARY_DEFAULT_MAX_VALUE = 28
    private const val DAY_MIN_VALUE = 1
    private const val MONTH_MIN_VALUE = 1
    private const val MONTH_MAX_VALUE = 12

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

    private fun Int.isFebruary() = this == FEBRUARY_MONTH_INDEX

    private fun Int.contains31Days() = MONTHS_WITH_31_DAYS.contains(this)

    private fun List<String>.toDateParameters(): DateParameters? = runCatching {
        DateParameters(
            year = get(YEAR_INDEX).toInt(),
            month = get(MONTH_INDEX).toInt(),
            day = get(DAY_INDEX).toInt()
        )
    }.getOrNull()

    private fun List<String>.isContainsValidDate(): Boolean = runCatching {
        val year = get(YEAR_INDEX).toInt()
        val month = get(MONTH_INDEX).toInt()
        val day = get(DAY_INDEX).toInt()
        val isMonthContainsCorrectNumberOfDays = when {
            month.isFebruary() && year.isLeapYear() && day <= DAY_FEBRUARY_LEAP_YEAR_MAX_VALUE -> true
            month.isFebruary() && !year.isLeapYear() && day <= DAY_FEBRUARY_DEFAULT_MAX_VALUE -> true
            month.contains31Days() && day <= DAY_MAX_VALUE -> true
            !month.contains31Days() && !month.isFebruary() && day <= DAY_30_DAYS_VALUE -> true
            else -> false
        }

        return get(YEAR_INDEX).length <= YEAR_LENGTH
            && month.toInt() <= MONTH_MAX_VALUE
            && month.toInt() >= MONTH_MIN_VALUE
            && isMonthContainsCorrectNumberOfDays
            && day <= DAY_MAX_VALUE
            && day >= DAY_MIN_VALUE
    }.getOrElse { false }

    private fun CompareDateArguments.invokeCompare() = baseDate.compareTo(compareDate)
}
