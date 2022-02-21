package operations.logic

import operations.ComparingOperation
import operations.logic.unwrap.BinaryValueBooleanUnwrapStrategy
import operations.logic.unwrap.SizeAliginingOperation
import utils.asList

internal interface NoArgumentSafeComparingOperation : ComparingOperation, SizeAliginingOperation, BinaryValueBooleanUnwrapStrategy {
    fun sizeSafeCompare(values: Any?, operator: (Int, Int) -> Boolean) = compareListOfTwo(fillValuesWithNullsIfNecessary(values.asList)) { first, second -> first == second }
}
