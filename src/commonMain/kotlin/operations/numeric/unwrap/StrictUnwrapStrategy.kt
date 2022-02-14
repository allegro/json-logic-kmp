package operations.numeric.unwrap

import asList

internal interface StrictUnwrapStrategy: UnwrapStrategy {
    override fun unwrapValues(wrappedValue: Any?): List<Any?> = wrappedValue.asList.map(::unwrap)

    private fun unwrap(value: Any?): Any? =
        when (value) {
            is Number -> value.toDouble()
            is String -> value.toDoubleOrNull()
            is List<*> -> unwrap(value.firstOrNull())
            else -> null
        }
}
