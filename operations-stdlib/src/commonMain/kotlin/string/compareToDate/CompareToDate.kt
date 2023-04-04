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

    override fun evaluateLogic(expression: Any?, data: Any?): Any? =
        expression.asList.toDateParametersList()?.invokeCompare()

    private fun List<Any?>.toDateParametersList(): List<DateParameters>? = runCatching {

        val baseDate = (get(DATE_INDEX) as String).splitDate()
        val compareDate = (get(COMPARING_DATE_INDEX) as String).splitDate()
        val baseDateParameters = baseDate.toDateParameters() ?: return null
        val compareDateParameters = compareDate.toDateParameters() ?: return null

        listOf(baseDateParameters, compareDateParameters)

    }.getOrNull()

    private fun String.splitDate() = split(DELIMITER)

    private fun List<String>.toDateParameters(): DateParameters? = runCatching {
        DateParameters(
            year = get(YEAR_INDEX).toInt(),
            month = get(MONTH_INDEX).toInt(),
            day = get(DAY_INDEX).toInt()
        )
    }.getOrNull()

    private fun List<DateParameters>.invokeCompare() = first().compareTo(last())

}
