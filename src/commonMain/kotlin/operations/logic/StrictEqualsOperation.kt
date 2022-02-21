package operations.logic

import utils.asList
import utils.secondOrNull

internal interface StrictEqualsOperation : DefectiveArgumentSafeComparingOperation {
    override fun sizeSafeCompare(values: Any?, operator: (Int, Int) -> Boolean): Boolean =
        with(complementValues(values.asList)) {
            if(firstOrNull() isTheSameType secondOrNull()) {
                compareListOfTwo(this) { first, second -> first == second }
            } else false
        }

    private infix fun Any?.isTheSameType(second: Any?) =
        (this == null && second == null) || (this != null && second != null && this::class == second::class)

    override fun complementValues(defectiveValues: List<Any?>) = when {
        defectiveValues.isEmpty() -> listOf(null, null)
        defectiveValues.size == 1 -> listOf(null, defectiveValues)
        else -> defectiveValues.map(::unwrapValue)
    }

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
