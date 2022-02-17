package operations.logic.unwrap

import operations.BooleanUnwrapStrategy

internal interface BinaryValueBooleanUnwrapStrategy : BooleanUnwrapStrategy {
    override fun unwrapValue(wrappedValue: Any?): Boolean? = when (wrappedValue) {
        is Boolean -> wrappedValue
        is Number -> wrappedValue.toLong().toBooleanOrNull()
        is String -> wrappedValue.toDoubleOrNull()?.toLong()?.toBooleanOrNull()
        else -> null
    }

    private fun Long.toBooleanOrNull() = when (this) {
        0L -> false
        1L -> true
        else -> null
    }
}
