package operations.numeric.unwrap

import asList
import operations.UnwrapStrategy

internal interface LenientUnwrapStrategy: UnwrapStrategy<List<Double?>> {

    override fun unwrapValues(wrappedValue: Any?): List<Double?> = unwrapAsDoubles(wrappedValue)

    fun unwrapAsDoubles(wrappedValue: Any?): List<Double?> = wrappedValue.asList.map(::unwrap)

    private fun unwrap(value: Any?): Double? =
        when (value) {
            is Number -> value.toDouble()
            is String -> value.toDoubleOrNull()
            is List<*> -> value.unwrap()
            is Boolean -> value.asNumber()
            null -> 0.0
            else -> null
        }

    private fun List<*>.unwrap() = when (size) {
        0 -> 0.0
        1 -> unwrap(first())
        else -> null
    }

    private fun Boolean.asNumber() = if (this) 1.0 else 0.0
}
