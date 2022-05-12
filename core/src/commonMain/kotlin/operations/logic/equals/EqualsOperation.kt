package operations.logic.equals

import operations.ComparingOperation
import operations.logic.unwrap.EqualsUnwrapStrategy
import operations.logic.unwrap.SingleNestedValueUnwrapStrategy
import utils.asList
import utils.secondOrNull

internal interface EqualsOperation : ComparingOperation, EqualsUnwrapStrategy, SingleNestedValueUnwrapStrategy {
    fun compare(values: Any?, operator: (Int, Int) -> Boolean): Boolean =
        with(values.asList) {
            val firstUnwrappedValue = unwrapSingleNestedValueOrDefault(firstOrNull())
            val secondUnwrappedValue = unwrapSingleNestedValueOrDefault(secondOrNull())
            val firstPossibleTrueValues = EqualsTableOfTruth[firstUnwrappedValue]
            val secondPossibleTrueValues = EqualsTableOfTruth[secondUnwrappedValue]

            if (firstPossibleTrueValues != null || secondPossibleTrueValues != null) {
                firstPossibleTrueValues?.contains(secondUnwrappedValue) ?: false
                    || secondPossibleTrueValues?.contains(firstUnwrappedValue) ?: false
            } else {
                compareListOfTwo(map(::unwrapValue), operator)
            }
        }
}
