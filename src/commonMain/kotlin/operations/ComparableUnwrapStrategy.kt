package operations

internal interface ComparableUnwrapStrategy {
    fun unwrapAsComparable(first: Comparable<*>?, second: Comparable<*>?): List<Comparable<*>?>? = when {
        first is Number && second is Number -> listOf(first.toDouble(), second.toDouble())
        first is String && second is Number -> listOf(first.toDoubleOrNull(), second.toDouble())
        first is Number && second is String -> listOf(first.toDouble(), second.toDoubleOrNull())
        first is Boolean || second is Boolean -> listOf(first.toBooleanOrNull(), second.toBooleanOrNull())
        else -> unwrapValuesAsNonPrimitives(first, second)
    }

    private fun Comparable<*>?.toBooleanOrNull(): Boolean? = when (this) {
        is Boolean -> this
        is Number -> this.toLong() > 0
        is String -> this.toDoubleOrNull()?.toLong()?.let { it > 0 }
        else -> null
    }

    private fun unwrapValuesAsNonPrimitives(first: Comparable<*>?, second: Comparable<*>?): List<Comparable<*>?>? = when {
        (first != null && second != null && first::class == second::class) -> listOf(first, second)
        first == null && second == null -> listOf(first, second)
        else -> null
    }
}
