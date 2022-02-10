package operations.numeric

import LogicOperation
import asDoubleList

object Subtraction : LogicOperation {
    override val key: String = "-"

    override fun invoke(expression: Any?, data: Any?) = with(expression?.asDoubleList.orEmpty()) {
        when (size) {
            0 -> null
            1 -> firstOrNull()?.unaryMinus()
            else -> (firstOrNull() ?: 0.0) - (getOrNull(1) ?: 0.0)
        }
    }
}
