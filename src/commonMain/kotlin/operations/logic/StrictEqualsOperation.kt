package operations.logic

import utils.asList
import utils.secondOrNull

internal interface StrictEqualsOperation : ListTypeSensitiveComparingOperation {
    override fun safeCompare(values: Any?, operator: (Int, Int) -> Boolean): Boolean {
        val unwrappedValues = values.asList.let {
            if (it.size == 1) {
                listOf(null, it)
            } else it.map(::unwrapValue)
        }

        return if (unwrappedValues.firstOrNull() isTheSameType unwrappedValues.secondOrNull()) {
            compareListOfTwo(unwrappedValues, operator)
        } else false
    }

    private infix fun Any?.isTheSameType(second: Any?) =
        (this == null && second == null) || (this != null && second != null && this::class == second::class)

    override fun unwrapValue(wrappedValue: Any?): Any? =
        when (wrappedValue) {
            is Number -> wrappedValue.toDouble()
            else -> wrappedValue
        }

    override fun unwrapValueAsBoolean(wrappedValue: Any?): Boolean? = when (wrappedValue) {
        is Boolean -> wrappedValue
        else -> null
    }
}
