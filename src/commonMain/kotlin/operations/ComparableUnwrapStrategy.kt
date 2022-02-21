package operations

internal interface ComparableUnwrapStrategy: BooleanUnwrapStrategy {
    fun unwrapAsComparable(first: Comparable<*>?, second: Comparable<*>?): List<Comparable<*>?>? = when {
        first is Number && second is Number -> listOf(first.toDouble(), second.toDouble())
        first is String && second is Number -> listOf(first.toDoubleOrNull(), second.toDouble())
        first is Number && second is String -> listOf(first.toDouble(), second.toDoubleOrNull())
        first is Boolean || second is Boolean -> listOf(unwrapValueAsBoolean(first), unwrapValueAsBoolean(second))
        else -> unwrapValuesAsNonPrimitives(first, second)
    }

    private fun unwrapValuesAsNonPrimitives(first: Comparable<*>?, second: Comparable<*>?): List<Comparable<*>?>? = when {
        (first != null && second != null && first::class == second::class) -> listOf(first, second)
        first == null && second == null -> listOf(first, second)
        else -> null
    }
}
