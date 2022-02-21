package operations.logic.unwrap

import utils.isSingleNullList

// rename
internal interface SizeAliginingOperation: EqualsUnwrapStrategy {

    // rename
    fun fillValuesWithNullsIfNecessary(values: List<Any?>) = when {
        values.isEmpty() -> listOf(null, null)
        values.all { it.isSingleNullList() } -> listOf(-1) // special case, return random false resulting value
        else -> values.map(::unwrapValue)
    }
}
