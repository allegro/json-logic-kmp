package string.compareToDate

import kotlinx.datetime.Instant
import operation.StandardLogicOperation
import string.StringUnwrapStrategy
import utils.asList

object CompareToDate : StandardLogicOperation, StringUnwrapStrategy {

    private const val DATE_INDEX = 0
    private const val COMPARING_DATE_INDEX = 1
    private const val PRECISION_COMPARING_INDEX = 2

    private val formatter = ComparePrecisionDateFormatter()
    override fun evaluateLogic(expression: Any?, data: Any?): Any? =
        expression.asList.toDateComparator()?.invokeCompare()

    private fun List<Any?>.toDateComparator(): List<Instant>? = runCatching {
        ComparePrecision.valueOf(get(PRECISION_COMPARING_INDEX) as String).let { precision ->
            listOf(
                formatter.formatDate((get(DATE_INDEX) as String), precision),
                formatter.formatDate((get(COMPARING_DATE_INDEX) as String), precision)
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
}
