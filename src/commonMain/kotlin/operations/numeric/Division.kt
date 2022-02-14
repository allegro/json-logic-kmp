package operations.numeric

import LogicOperation
import asDoubleList
import asList
import secondOrNull

object Division : LogicOperation {
    override val key: String = "/"

    override fun invoke(expression: Any?, data: Any?) =
        expression.unwrapValues().takeIf { it.size >= 2 }?.let {
            val second = it[1]
            val first = it.first()
            if(first != null && second != null && second != 0.0) {
                first / second
            } else null
        }

    // TODO extract unwrapping to some interface because many mathematical operations do with their own style
    private fun Any?.unwrapValues() = asList.map(::unwrap)

    private fun unwrap(value: Any?): Double? =
        when (value) {
            is Number -> value.toDouble()
            is String -> value.toDoubleOrNull()
            is List<*> -> value.unwrap()
            is Boolean -> value.asNumber()
            null -> 0.0
            else -> null
        }

    private fun List<*>.unwrap() = when(size) {
        0 -> 0.0
        1 -> unwrap(first())
        else -> null
    }

    private fun Boolean.asNumber() = if(this) 1.0 else 0.0
}
