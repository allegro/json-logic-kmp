package operations.numeric.unwrap

import asList

internal interface StrictUnwrapStrategy: UnwrapStrategy {
    override fun Any?.unwrapValues() = asList.map(::unwrap)

    private fun unwrap(value: Any?): Any? =
        when (value) {
            is Number -> value.toDouble()
            is String -> value.toDoubleOrNull()
            is List<*> -> unwrap(value.firstOrNull())
            else -> null
        }
}
