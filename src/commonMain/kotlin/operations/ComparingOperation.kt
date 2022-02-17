package operations

import utils.comparableList
import utils.secondOrNull

internal interface ComparingOperation : BooleanUnwrapStrategy {
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
        val castedFirst = unwrapValue(first)
        val castedSecond = unwrapValue(second)
        return if (castedFirst != null && castedSecond != null) {
            compareValues(castedFirst, castedSecond)
        } else null
    }

    // wydzielic do osobnego interfejsu


    private fun nonPrimitiveCompare(first: Comparable<*>?, second: Comparable<*>?): Int? {
        return if (first != null && second != null && first::class == second::class) {
            compareValues(first, second)
        } else null
    }
}
