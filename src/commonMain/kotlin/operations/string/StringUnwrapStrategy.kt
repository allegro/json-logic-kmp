package operations.string

import asList
import operations.UnwrapStrategy
import toStringOrEmpty

internal interface StringUnwrapStrategy : UnwrapStrategy<List<String>> {

    override fun unwrapValues(wrappedValue: Any?): List<String> = wrappedValue.asList.map(::stringify)

    private fun stringify(value: Any?) = (value as? List<*>)?.flatMap { nestedValue ->
        nestedValue.flattenNestedLists()
    }?.joinToString(separator = ",") ?: value.formatAsString()

    private fun Any?.flattenNestedLists(): List<String> = (this as? List<*>)?.flatMap {
        it.flattenNestedLists()
    } ?: listOf(formatAsString())

    private fun Any?.formatAsString(): String {
        return when {
            (this is Number && toDouble() == toInt().toDouble()) -> toInt().toString()
            else -> toStringOrEmpty()
        }
    }
}
