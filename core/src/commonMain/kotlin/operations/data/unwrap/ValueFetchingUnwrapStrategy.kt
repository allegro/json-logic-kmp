package operations.data.unwrap

internal interface ValueFetchingUnwrapStrategy {
    fun unwrapDataKeys(expression: Any?): List<String>? {
        val indexParts = if (expression is List<*>) {
            expression.unwrapNestedValue()
        } else {
            expression
        }

        return if (indexParts is List<*>) {
            null
        } else {
            indexParts?.toString()?.split(".").orEmpty()
        }
    }

    private fun List<*>.unwrapNestedValue(): Any? {
        val firstValue = firstOrNull()
        return when {
            firstValue is List<*> -> firstValue.unwrapNested()
            firstValue.isFetchWholeDataValue() -> null
            else -> firstValue
        }
    }

    private fun Any?.isFetchWholeDataValue() = listOf(null, "", emptyList<Any>()).contains(this)

    private fun List<*>.unwrapNested() = if (size > 1) {
        this
    } else {
        unwrapNestedValue() ?: this
    }
}
