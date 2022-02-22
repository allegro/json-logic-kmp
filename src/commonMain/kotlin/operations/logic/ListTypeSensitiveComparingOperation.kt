package operations.logic

import operations.ComparingOperation
import operations.logic.unwrap.EqualsUnwrapStrategy
import utils.asList

internal interface ListTypeSensitiveComparingOperation : ComparingOperation, EqualsUnwrapStrategy {
    fun safeCompare(
        values: Any?,
        operator: (Int, Int) -> Boolean
    ): Boolean = compareListOfTwo(values.asList, operator)
}
