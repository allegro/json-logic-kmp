package operations.logic

import operations.ComparingOperation
import operations.logic.unwrap.EqualsUnwrapStrategy
import utils.asList

internal interface DefectiveArgumentSafeComparingOperation : ComparingOperation, EqualsUnwrapStrategy {
    fun sizeSafeCompare(values: Any?, operator: (Int, Int) -> Boolean) = compareListOfTwo(complementValues(values.asList), operator)
    fun complementValues(defectiveValues: List<Any?>): List<Any?>
}
