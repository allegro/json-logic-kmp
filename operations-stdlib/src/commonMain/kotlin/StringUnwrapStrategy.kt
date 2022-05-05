import utils.asList
import utils.toStringOrEmpty

internal interface StringUnwrapStrategy2 {
    fun unwrapValueAsString(wrappedValue: Any?): List<String> = wrappedValue.asList.map(::stringify)

    private fun stringify(value: Any?) = (value as? List<*>)?.flatMap { nestedValue ->
        nestedValue.flattenNestedLists()
    }?.joinToString(separator = ",") ?: value.formatAsString()

    private fun Any?.flattenNestedLists(): List<String> = (this as? List<*>)?.flatMap {
        it.flattenNestedLists()
    } ?: listOf(formatAsString())

    private fun Any?.formatAsString(): String = if (this is Number && toDouble() == toInt().toDouble()) {
        toInt().toString()
    } else toStringOrEmpty()
}
