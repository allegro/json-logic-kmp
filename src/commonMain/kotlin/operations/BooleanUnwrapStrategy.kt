package operations

interface BooleanUnwrapStrategy {
    fun unwrapValueAsBoolean(wrappedValue: Any?): Boolean? = when (wrappedValue) {
        is Boolean -> wrappedValue
        is Number -> wrappedValue.toLong() > 0
        is String -> wrappedValue.toDoubleOrNull()?.toLong()?.let { it > 0 }
        else -> null
    }
}
