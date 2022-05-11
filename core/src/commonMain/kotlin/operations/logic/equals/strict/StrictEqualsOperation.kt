package operations.logic.equals.strict

import operations.logic.equals.EqualsOperation
import utils.asList

internal interface StrictEqualsOperation : EqualsOperation {
    override fun compare(values: Any?, operator: (Int, Int) -> Boolean): Boolean {
        return with(values.asList) {
            if (size != 1) {
                compareListOfTwo(map(::unwrapValue), operator)
            } else false
        }
    }
    override fun unwrapValue(wrappedValue: Any?): Any? =
        when (wrappedValue) {
            is Number -> wrappedValue.toDouble()
            else -> wrappedValue
        }

    override fun unwrapAsComparable(first: Comparable<*>?, second: Comparable<*>?): List<Comparable<*>?>? =
        unwrapAsComparableWithTypeSensitivity(first, second)
}
