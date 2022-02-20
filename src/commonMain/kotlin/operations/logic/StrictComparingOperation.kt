package operations.logic

import utils.asList
import utils.secondOrNull

internal interface StrictComparingOperation : NoArgumentSafeComparingOperation {
    override fun sizeSafeCompare(value: Any?, operator: (Int, Int) -> Boolean): Boolean {
        return with(value.asList) {
            val preparedValue = (if( size < 2 ) {
                sprawdzTo(this)
            } else this).map(::unwrapValues)

            if (!nullSafeTypeCompare(preparedValue.firstOrNull(), preparedValue.secondOrNull())) {
                false
            } else {
                compareListOfTwo(preparedValue) { first, second -> first == second }
            }
        }
    }

    private fun nullSafeTypeCompare(first: Any?, second: Any?) =
        (first == null && second == null) || (first != null && second != null && first::class == second::class)

    // align to default
    // polaczyc to z unwrapValues i sprawdzTo
    private fun sprawdzTo(values: List<Any?>) = if(values.isEmpty()) {
            listOf(null, null)
        } else {
            listOf(null, listOf(values.firstOrNull())) // wsadz aktualny argument w liste i dodaj razem z nullem do kolejnej listy
        }

    override fun unwrapValues(wrappedValue: Any?): Any? =
        when (wrappedValue) {
            is Number -> wrappedValue.toDouble()
            else -> wrappedValue
        }

    override fun unwrapValue(wrappedValue: Any?): Boolean? = when (wrappedValue) {
        is Boolean -> wrappedValue
        else -> null
    }
}
