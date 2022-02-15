package operations.string

import asList
import operations.UnwrapStrategy
import toStringOrEmpty

// rename functions
internal interface StringUnwrapStrategy: UnwrapStrategy<List<String>> {
    override fun unwrapValues(wrappedValue: Any?): List<String> = wrappedValue.asList.map(::stringify)

    fun unwrapAsStrings(wrappedValue: Any?): List<String> = wrappedValue.asList.map(::stringify)

    private fun stringify(value: Any?) = (value as? List<*>)?.flatMap { nestedValue ->
        nestedValue.flattenNestedLists()
    }?.joinToString(separator = ",") ?: value.formatValues()

    private fun Any?.flattenNestedLists(): List<String> = (this@flattenNestedLists as? List<*>)?.flatMap {
        it.flattenNestedLists()
    } ?: listOf(this@flattenNestedLists.formatValues())

    private fun Any?.formatValues(): String {
        return when {
            (this is Number && toDouble() == toInt().toDouble()) -> toInt().toString()
            else -> this@formatValues.toStringOrEmpty()
        }
    }
}
