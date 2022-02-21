package operations.logic

import utils.asList
import utils.secondOrNull

internal interface StrictComparingOperation : NoArgumentSafeComparingOperation {
    override fun sizeSafeCompare(values: Any?, operator: (Int, Int) -> Boolean): Boolean =
        with(fillValuesWithNullsIfNecessary(values.asList)) {
            if (!nullSafeTypeCompare(firstOrNull(), secondOrNull())) {
                false
            } else {
                compareListOfTwo(this) { first, second -> first == second }
            }
        }

    private fun nullSafeTypeCompare(first: Any?, second: Any?) =
        (first == null && second == null) || (first != null && second != null && first::class == second::class)

    override fun fillValuesWithNullsIfNecessary(values: List<Any?>) = when {
        values.isEmpty() -> listOf(null, null)
        values.size == 1 -> listOf(null, values)
        else -> values.map(::unwrapValue)
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
