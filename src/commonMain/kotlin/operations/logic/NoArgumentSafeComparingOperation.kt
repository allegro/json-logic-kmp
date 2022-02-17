package operations.logic

import operations.ComparingOperation
import operations.logic.unwrap.EqualsUnwrapStrategy

internal interface NoArgumentSafeComparingOperation : ComparingOperation, EqualsUnwrapStrategy {
    fun sizeSafeCompare(values: List<Any?>?, operator: (Int, Int) -> Boolean) = if (isValueEmpty(values)) {
        true
    } else {
        compareListOfTwo(values?.map(::unwrapValues)) { first, second -> first == second }
    }

    fun isValueEmpty(value: Any?) = when (value) {
        is List<*> -> value.isEmpty() || value.all { it == null }
        null -> true
        else -> false
    }
}
