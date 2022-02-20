package operations.logic.unwrap

import utils.asNumber
import utils.isListWithSingleNull

internal interface EqualsUnwrapStrategy {
    // TODO rename
    fun unwrapValue(wrappedValue: Any?): Any? =
        when  {
            wrappedValue is Number -> wrappedValue.toDouble()
            wrappedValue is String -> wrappedValue.toDoubleOrNull() ?: wrappedValue
            wrappedValue.isListWithSingleNull() -> 0.0
            wrappedValue  is List<*> -> wrappedValue.takeIf { it.size == 1 && it.firstOrNull() !is Boolean }
                ?.let { unwrapValue(wrappedValue.firstOrNull()) } ?: wrappedValue
            wrappedValue is Boolean -> wrappedValue.asNumber()
            else -> wrappedValue
        }
}
