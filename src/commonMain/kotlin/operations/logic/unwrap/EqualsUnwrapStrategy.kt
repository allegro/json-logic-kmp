package operations.logic.unwrap

internal interface EqualsUnwrapStrategy : BinaryValueBooleanUnwrapStrategy {
    // TODO rename
    fun unwrapValues(wrappedValue: Any?): Any? =
        when (wrappedValue) {
            is Number -> wrappedValue.toDouble()
            is String -> wrappedValue.toDoubleOrNull() ?: wrappedValue
            is List<*> -> wrappedValue.takeIf { it.size == 1 && it.firstOrNull() !is Boolean }
                ?.let { unwrapValues(wrappedValue.firstOrNull()) }
            else -> wrappedValue
        }

    override fun unwrapValue(wrappedValue: Any?): Boolean? = when (wrappedValue) {
        is Boolean -> wrappedValue
        is Number -> wrappedValue.toLong().toBooleanOrNull()
        is String -> wrappedValue.toDoubleOrNull()?.toLong()?.toBooleanOrNull()
        else -> null
    }

    private fun Long.toBooleanOrNull() = when (this) {
        0L -> false
        1L -> true
        else -> null
    }
}
