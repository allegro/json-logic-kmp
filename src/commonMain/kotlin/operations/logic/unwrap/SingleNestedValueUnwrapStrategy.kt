package operations.logic.unwrap

internal interface SingleNestedValueUnwrapStrategy {
    fun unwrapSingleNestedValueOrDefault(value: Any?) = value.unwrapSingleNestedValue().let {
        if (it != value) {
            SingleNestedValue(it)
        } else value
    }

    private fun Any?.unwrapSingleNestedValue(): Any? = when {
        this is List<*> && this.size == 1 -> this.firstOrNull().unwrapSingleNestedValue()
        else -> this
    }
}
