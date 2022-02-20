package operations.numeric.unwrap

import utils.asList

internal interface StrictUnwrapStrategy {
    fun unwrapValue(wrappedValue: Any?): List<Any?> = wrappedValue.asList.map(::unwrap)

    private tailrec fun unwrap(value: Any?): Any? =
        when (value) {
            is Number -> value.toDouble()
            is String -> value.toDoubleOrNull()
            is List<*> -> unwrap(value.firstOrNull())
            else -> null
        }
}
