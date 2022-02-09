package operations.numeric

import comparableList
import kotlin.comparisons.compareValues

internal interface ComparingOperation {
    fun compareOrNull(first: Comparable<*>?, second: Comparable<*>?) = when {
        first is Number && second is Number -> compareValues(first.toDouble(), second.toDouble())
        first is String && second is Number -> first.toDoubleOrNull()?.let {
            compareValues(it, second.toDouble())
        }
        first is Number && second is String -> second.toDoubleOrNull()?.let {
            compareValues(first.toDouble(), it)
        }
        first is String && second is String -> compareValues(first.toString(), second.toString())
        // probably this might turn into else branch
        else -> {
            val castedFirst = first.toBooleanOrNull()
            val castedSecond = second.toBooleanOrNull()
            if(castedFirst != null && castedSecond != null) {
                compareValues(castedFirst, castedSecond)
            } else null
        }
    }

    fun List<Any?>?.compareListOfTwo(operator: (Int, Int) -> Boolean) = this?.comparableList?.takeIf { it.size >= 2 }?.compare(operator) ?: false

    fun List<Any?>?.compareOrBetween(operator: (Int, Int) -> Boolean) = with(this?.comparableList) {
        when (this?.size) {
            2 -> this.compare(operator)
            3 -> this.between(operator)
            else -> false
        }
    }

    private fun Any?.toBooleanOrNull() = when (this) {
        is Boolean -> this
        is Number -> toInt() > 0
        is String -> toBooleanStrictOrNull()
        else -> null
    }

    private fun List<Comparable<*>?>.compare(operator: (Int, Int) -> Boolean): Boolean {
        return compareOrNull(firstOrNull(), getOrNull(1))?.let { operator(it, 0) } ?: false
    }

    private fun List<Comparable<*>?>.between(operator: (Int, Int) -> Boolean): Boolean {
        val firstEvaluation = compareOrNull(firstOrNull(), getOrNull(1))
        val secondEvaluation = compareOrNull(getOrNull(1), getOrNull(2))

        return if(firstEvaluation != null && secondEvaluation != null) {
            operator(firstEvaluation, 0) && operator(secondEvaluation, 0)
        } else false
    }
}
