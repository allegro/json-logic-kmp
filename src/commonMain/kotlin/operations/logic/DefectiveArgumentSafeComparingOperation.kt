package operations.logic

import operations.ComparingOperation
import operations.logic.unwrap.BinaryValueBooleanUnwrapStrategy
import operations.logic.unwrap.EqualsUnwrapStrategy
import utils.asList
import utils.isSingleNullList

internal interface DefectiveArgumentSafeComparingOperation : ComparingOperation, EqualsUnwrapStrategy, BinaryValueBooleanUnwrapStrategy {
    fun sizeSafeCompare(values: Any?, operator: (Int, Int) -> Boolean) = compareListOfTwo(fillValuesWithNullsIfNecessary(values.asList)) { first, second -> first == second }
    // rename
    fun fillValuesWithNullsIfNecessary(values: List<Any?>) = when {
        values.isEmpty() -> listOf(null, null)
        values.all { it.isSingleNullList() } -> listOf(-1) // special case, return random false resulting value
        else -> values.map(::unwrapValue)
    }
}
