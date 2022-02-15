package operations.numeric.unwrap

import utils.asList
import operations.UnwrapStrategy

internal interface StrictUnwrapStrategy: UnwrapStrategy<List<Any?>> {
    override fun unwrapValues(wrappedValue: Any?): List<Any?> = wrappedValue.asList.map(::unwrap)

    private fun unwrap(value: Any?): Any? =
        when (value) {
            is Number -> value.toDouble()
            is String -> value.toDoubleOrNull()
            is List<*> -> unwrap(value.firstOrNull())
            else -> null
        }
}
