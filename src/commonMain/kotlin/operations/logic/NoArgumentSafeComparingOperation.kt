package operations.logic

import operations.ComparingOperation
import operations.logic.unwrap.EqualsUnwrapStrategy

internal interface NoArgumentSafeComparingOperation : ComparingOperation, EqualsUnwrapStrategy {
    fun sizeSafeCompare(values: List<Any?>?, operator: (Int, Int) -> Boolean) = if (values.isEmpty()) {
        true
    } else {
        compareListOfTwo(values?.map(::unwrapValues)) { first, second -> first == second }
    }

    private fun Any?.isEmpty() = when (this) {
        null -> true
        is List<*> -> isEmpty() || all { it == null }
        else -> false
    }
}
