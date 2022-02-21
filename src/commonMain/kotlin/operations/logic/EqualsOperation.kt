package operations.logic

import utils.asList
import utils.isSingleNullList

internal interface EqualsOperation : DefectiveArgumentSafeComparingOperation {
    override fun sizeSafeCompare(
        values: Any?,
        operator: (Int, Int) -> Boolean
    ) = compareListOfTwo(complementValues(values.asList), operator)

    override fun complementValues(defectiveValues: List<Any?>): List<Any?> = when {
        defectiveValues.isEmpty() -> listOf(null, null)
        defectiveValues.all { it.isSingleNullList() } -> listOf(-1) // special case, return random false resulting value
        else -> defectiveValues.map(::unwrapValue)
    }

    override fun unwrapValueAsBoolean(wrappedValue: Any?): Boolean? = when (wrappedValue) {
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
