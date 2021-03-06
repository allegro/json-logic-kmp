package operations.logic.unwrap

internal interface TruthyUnwrapStrategy {
    fun unwrapValueAsBoolean(wrappedValue: Any?): Boolean = when (wrappedValue) {
        null -> false
        is Boolean -> wrappedValue
        is Number -> wrappedValue.toDouble() != 0.0
        is String -> wrappedValue.isNotEmpty() && wrappedValue != "[]" && wrappedValue != "null"
        is Collection<*> -> wrappedValue.isNotEmpty()
        is Array<*> -> wrappedValue.isNotEmpty()
        else -> true
    }
}
