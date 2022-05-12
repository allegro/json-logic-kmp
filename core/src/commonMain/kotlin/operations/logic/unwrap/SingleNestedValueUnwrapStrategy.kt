package operations.logic.unwrap

internal interface SingleNestedValueUnwrapStrategy {
    fun unwrapSingleNestedValueOrDefault(value: Any?) = value.unwrapSingleNestedValue().let {
        if (it != value) {
            SingleNestedValue(normalizeNumberString(it))
        } else normalizeNumberString(value)
    }

    private fun Any?.unwrapSingleNestedValue(): Any? = when {
        this is List<*> && this.size == 1 -> this.firstOrNull().unwrapSingleNestedValue()
        else -> this
    }

    private fun normalizeNumberString(value: Any?) = (value as? String).let {
        (it?.toIntOrNull() ?: it?.toDoubleOrNull())?.toString() ?: value
    }
}
