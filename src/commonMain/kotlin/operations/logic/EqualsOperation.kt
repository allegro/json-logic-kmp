package operations.logic

import utils.comparableList
import utils.secondOrNull

internal interface EqualsOperation : ListTypeSensitiveComparingOperation {
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
        return if (first is List<*> && second is List<*>) {
            null // JS compares lists by their references, so it always returns false/null
        } else {
            listOf(first, second).map(::unwrapValue).comparableList.let {
                super.compareOrNull(it.firstOrNull(), it.secondOrNull())
            }
        }
    }
}
