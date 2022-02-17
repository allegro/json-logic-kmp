package operations

import utils.comparableList
import utils.secondOrNull

internal interface ComparingOperation {
    fun compareListOfTwo(values: List<Any?>?, operator: (Int, Int) -> Boolean) = values?.comparableList
        ?.takeIf { it.size >= 2 }
        ?.let { compare(it, operator) } ?: false

    fun compare(values: List<Comparable<*>?>, operator: (Int, Int) -> Boolean): Boolean {
        return compareOrNull(values.firstOrNull(), values.secondOrNull())?.let { operator(it, 0) } ?: false
    }

    fun compareOrNull(first: Comparable<*>?, second: Comparable<*>?) = when {
        first is Number && second is Number -> compareValues(first.toDouble(), second.toDouble())
        first is String && second is Number -> first.toDoubleOrNull()?.let {
            compareValues(it, second.toDouble())
        }
        first is Number && second is String -> second.toDoubleOrNull()?.let {
            compareValues(first.toDouble(), it)
        }
        first is Boolean || second is Boolean -> booleanCompare(first, second)
        else -> nonPrimitiveCompare(first, second)
    }


    private fun booleanCompare(first: Comparable<*>?, second: Comparable<*>?): Int? {
        val castedFirst = first.toBooleanOrNull()
        val castedSecond = second.toBooleanOrNull()
        return if (castedFirst != null && castedSecond != null) {
            compareValues(castedFirst, castedSecond)
        } else null
    }

    private fun Any?.toBooleanOrNull() = when (this) {
        is Boolean -> this
        is Number -> toLong() > 0
        is String -> toDoubleOrNull()?.toLong()?.let { it > 0 }
        else -> null
    }

    private fun nonPrimitiveCompare(first: Comparable<*>?, second: Comparable<*>?): Int? {
        return if (first != null && second != null && first::class == second::class) {
            compareValues(first, second)
        } else null
    }
}
