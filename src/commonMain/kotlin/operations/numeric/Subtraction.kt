package operations.numeric

import LogicOperation
import asDoubleList
import asList
import secondOrNull

object Subtraction : LogicOperation {
    override val key: String = "-"

    override fun invoke(expression: Any?, data: Any?) = with(expression.unwrapValues()) {
        when (size) {
            0 -> null
            1 -> first()?.unaryMinus()
            else -> {
                val second = get(1)
                val first = first()
                if(first != null && second != null) {
                    first - second
                } else null
            }
        }
    }

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
