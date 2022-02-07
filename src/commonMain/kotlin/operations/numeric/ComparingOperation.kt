package operations.numeric

import comparableList
import doubleOrZero
import truthy

internal interface ComparingOperation {
    fun compare(first: Comparable<*>?, second: Comparable<*>?) = when {
        first is Number && second is Number -> compareValues(first.toDouble(), second.toDouble())
        first is String && second is Number -> compareValues(first.toDoubleOrNull(), second.toDouble())
        first is Number && second is String -> compareValues(first.toDouble(), second.toDoubleOrNull())
        // remove
        first is String && second is String -> compareValues(first.toString(), second.toString())
        first is Boolean || second is Boolean -> compareValues(first.truthy, second.truthy)
        else -> compareValues(first, second)
    }

    fun compareStrict(first: Comparable<*>?, second: Comparable<*>?) = when {
        first is Number && second is Number -> compareValues(first.toDouble(), second.toDouble())
        // remove
        first is String && second is String -> compareValues(first, second)
        else -> -1
    }

    fun List<Any?>?.compareListOfThree(operator: (Int, Int) -> Boolean) = with(this?.comparableList) {
        when (this?.size) {
            2 -> operator(compare(firstOrNull(), getOrNull(1)), 0)
            3 -> operator(compare(firstOrNull(), getOrNull(1)), 0)
                    && operator(compare(getOrNull(1), getOrNull(2)), 0)
            else -> false
        }
    }
}
