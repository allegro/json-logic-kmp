package operations.logic.unwrap

import utils.asNumber
import utils.isSingleNullList

internal interface EqualsUnwrapStrategy {
    fun unwrapValue(wrappedValue: Any?): Any? =
        when (wrappedValue) {
            is Number -> wrappedValue.toDouble()
            is String -> wrappedValue.toDoubleOrNull() ?: wrappedValue
            is List<*> -> wrappedValue.unwrapList() ?: wrappedValue
            is Boolean -> wrappedValue.asNumber()
            else -> wrappedValue
        }

    private fun List<*>.unwrapList() = if (this.isSingleNullList()) 0.0 else unwrapNotBooleanSingleElement()

    private fun List<*>.unwrapNotBooleanSingleElement() = takeIf { size == 1 && firstOrNull() !is Boolean }
        ?.let { unwrapValue(firstOrNull()) }
}
