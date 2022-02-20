package operations.logic.unwrap

import utils.asNumber

internal interface EqualsUnwrapStrategy : BinaryValueBooleanUnwrapStrategy {
    // TODO rename
    fun unwrapValues(wrappedValue: Any?): Any? =
        when  {
            wrappedValue is Number -> wrappedValue.toDouble()
            wrappedValue is String -> wrappedValue.toDoubleOrNull() ?: wrappedValue
            isListWithSingleNull(wrappedValue) -> 0.0
            wrappedValue  is List<*> -> wrappedValue.takeIf { it.size == 1 && it.firstOrNull() !is Boolean }
                ?.let { unwrapValues(wrappedValue.firstOrNull()) } ?: wrappedValue
            wrappedValue is Boolean -> wrappedValue.asNumber()
            else -> wrappedValue
        }

    fun isListWithSingleNull(value: Any?) = value is List<*> && value.size == 1 && value.first() == null
}
