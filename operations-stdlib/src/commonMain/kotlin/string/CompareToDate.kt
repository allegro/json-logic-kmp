package string

import operation.StandardLogicOperation
import utils.asList

object CompareToDate: StandardLogicOperation, StringUnwrapStrategy {

    private const val DATE_INDEX = 0
    private const val COMPARING_DATE_INDEX = 1

    private const val YEAR_INDEX = 0
    private const val MONTH_INDEX = 1
    private const val DAY_INDEX = 2

    private const val DAY_MAX_VALUE = 31
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
    }.fold(
        onSuccess = { it },
        onFailure = { null }
    )

    private fun String.splitDate(): List<String> { return this.split("-") }
    private fun List<String>.toDateParameters(): DateParameters? = runCatching {
        DateParameters(
            year = get(YEAR_INDEX).toInt(),
            month = get(MONTH_INDEX).toInt(),
            day = get(DAY_INDEX).toInt()
        )
    }.fold(
        onSuccess = { it },
        onFailure = { null }
    )

    private fun List<String>.isContainsValidDate(): Boolean = runCatching {
        val month = get(MONTH_INDEX)
        val day = get(DAY_INDEX)
        return get(YEAR_INDEX).length <= 4
            && month.length == 2
            && month.toInt() <= MONTH_MAX_VALUE
            && month.toInt() >= MONTH_MIN_VALUE
            && day.length == 2
            && day.toInt() <= DAY_MAX_VALUE
            && day.toInt() >= DAY_MIN_VALUE
            this.size == 3
    }.fold(
        onSuccess = { it },
        onFailure = { false }
    )

    private fun CompareDateArguments.invokeCompare(): DateComparationStatus? = runCatching {
        val yearComparationStatus = this.compareYears() ?: return null
        val monthComparationStatus = this.compareMonth() ?: return null
        val dayComparationStatus = this.compareDay() ?: return null

        return if (yearComparationStatus != DateComparationStatus.EQUALS) {
            yearComparationStatus
        } else if (monthComparationStatus != DateComparationStatus.EQUALS) {
            monthComparationStatus
        } else {
            dayComparationStatus
        }
    }.fold(
        onSuccess = { it },
        onFailure = { null }
    )

    private fun CompareDateArguments.compareYears(): DateComparationStatus? = when {
        baseDate.year == compareDate.year -> DateComparationStatus.EQUALS
        baseDate.year > compareDate.year -> DateComparationStatus.BEFORE
        baseDate.year < compareDate.year -> DateComparationStatus.AFTER
        else -> null
    }

    private fun CompareDateArguments.compareMonth(): DateComparationStatus? = when {
        baseDate.month == compareDate.month -> DateComparationStatus.EQUALS
        baseDate.month > compareDate.month -> DateComparationStatus.BEFORE
        baseDate.month < compareDate.month -> DateComparationStatus.AFTER
        else -> null
    }

    private fun CompareDateArguments.compareDay(): DateComparationStatus? = when {
        baseDate.day == compareDate.day -> DateComparationStatus.EQUALS
        baseDate.day > compareDate.day -> DateComparationStatus.BEFORE
        baseDate.day < compareDate.day -> DateComparationStatus.AFTER
        else -> null
    }
}

private data class CompareDateArguments(
    val baseDate: DateParameters,
    val compareDate: DateParameters
)
private data class DateParameters(
    val year: Int,
    val month: Int,
    val day: Int

)
enum class DateComparationStatus {
    AFTER, BEFORE, EQUALS
}

