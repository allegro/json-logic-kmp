package operations.logic

import utils.asList
import utils.comparableList
import utils.isSingleNullList
import utils.secondOrNull

internal interface EqualsOperation : DefectiveArgumentSafeComparingOperation {
    override fun sizeSafeCompare(
        values: Any?,
        operator: (Int, Int) -> Boolean
//    ) = compareListOfTwo(complementValues(values.asList), operator)
    ) = compareListOfTwo(values.asList, operator)

    override fun complementValues(defectiveValues: List<Any?>): List<Any?> = when {
//        defectiveValues.isEmpty() -> listOf(null, null)
//        defectiveValues.isNotEmpty() && defectiveValues.all { it.isSingleNullList() } -> listOf(-1) // special case, return random false resulting value
        else -> defectiveValues.map(::unwrapValue)
//        else -> this
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
override fun compareOrNull(first: Comparable<*>?, second: Comparable<*>?): Int? {
    return when {
        first is List<*> && second is List<*> -> null // JS compares lists by reference so it always returns false
        else -> complementValues(listOf(first, second)).comparableList.let {
            super.compareOrNull(it.firstOrNull(), it.secondOrNull())
        }
    }
    }
}
