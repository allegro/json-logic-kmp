package operations.logic

import operations.ComparingOperation
import operations.logic.unwrap.EqualsUnwrapStrategy
import utils.asList

internal interface NoArgumentSafeComparingOperation : ComparingOperation, EqualsUnwrapStrategy {
    fun sizeSafeCompare(values: Any?, operator: (Int, Int) -> Boolean) = compareListOfTwo(sprawdzTo(values.asList)) { first, second -> first == second }

    // sprawdzic czy tego nie da sie spoic z EqualsUnwrapStrategy.unwrapValues()
    private fun sprawdzTo(values: List<Any?>) = when {
        values.isEmpty() -> listOf(null, null)
        values.size == 1 -> when {
            isListWithSingleNull(values.first()) -> listOf(null, null)
            values.first() == null -> listOf(null, null)
            else -> listOf(null, listOf(values.first()))
        }
        values.all { isListWithSingleNull(it) } -> listOf(-1) // special case, return random false resulting value
        else -> values.map(::unwrapValues)
    }
}
