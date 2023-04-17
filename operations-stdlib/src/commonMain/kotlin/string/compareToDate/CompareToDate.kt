package string.compareToDate

import kotlinx.datetime.Instant
import operation.StandardLogicOperation
import string.StringUnwrapStrategy
import utils.asList

object CompareToDate: StandardLogicOperation, StringUnwrapStrategy {

    private const val DATE_INDEX = 0
    private const val COMPARING_DATE_INDEX = 1
    private const val PRECISION_COMPARING_INDEX = 2

    override fun evaluateLogic(expression: Any?, data: Any?): Any? =
        expression.asList.toDateComparator()?.invokeCompare()

    private fun List<Any?>.toDateComparator(): List<Instant>? = runCatching {
        ComparePrecision.valueOf(get(PRECISION_COMPARING_INDEX) as String).let { precision ->
            listOf(
                Instant.parse((get(DATE_INDEX) as String).formatDate(precision)),
                Instant.parse((get(COMPARING_DATE_INDEX) as String).formatDate(precision))
            )
        }
    }.getOrNull()

    private fun List<Instant>.invokeCompare(): Int? = runCatching {
        first().compareTo(last())
    }.onSuccess {
        return when {
            it > 0 -> 1
            it < 0 -> -1
            else -> 0
        }
    }.getOrNull()

    private fun String.formatDate(precision: ComparePrecision) =
        substring(0,precision.position()) + precision.dateSuffix()
}
