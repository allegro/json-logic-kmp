package operations

import utils.comparableList
import utils.secondOrNull

internal interface ComparingOperation : ComparableUnwrapStrategy {
    fun compareListOfTwo(values: List<Any?>?, operator: (Int, Int) -> Boolean) = values?.comparableList
        ?.let { compare(it, operator) } ?: false

    private fun compare(values: List<Comparable<*>?>, operator: (Int, Int) -> Boolean): Boolean {
        return compareOrNull(values.firstOrNull(), values.secondOrNull())?.let { operator(it, 0) } ?: false
    }

    fun compareOrNull(
        first: Comparable<*>?,
        second: Comparable<*>?
    ) = unwrapAsComparable(first, second)?.let { values ->
        when {
            values.all { value -> value == null } -> compareValues(values.firstOrNull(), values.secondOrNull())
            values.any { value -> value == null } -> null
            else -> compareValues(values.firstOrNull(), values.secondOrNull())
        }
    }

}
