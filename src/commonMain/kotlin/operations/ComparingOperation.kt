package operations

import utils.comparableList
import utils.secondOrNull

internal interface ComparingOperation : ComparableUnwrapStrategy {
    fun compareListOfTwo(values: List<Any?>?, operator: (Int, Int) -> Boolean) = values?.comparableList
        ?.let { compare(it, operator) } ?: false

    fun compare(values: List<Comparable<*>?>, operator: (Int, Int) -> Boolean): Boolean {
        return compareOrNull(values.firstOrNull(), values.secondOrNull())?.let { operator(it, 0) } ?: false
    }

    fun compareOrNull(first: Comparable<*>?, second: Comparable<*>?) = unwrapAsComparable(first, second)?.let {
            when {
                it.firstOrNull() == null && it.secondOrNull() == null -> compareValues(it.firstOrNull(), it.secondOrNull())
                it.firstOrNull() == null || it.secondOrNull() == null -> null
                else -> compareValues(it.firstOrNull(), it.secondOrNull())
            }
        }
}
