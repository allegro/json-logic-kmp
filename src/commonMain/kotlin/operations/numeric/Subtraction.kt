package operations.numeric

import LogicOperation
import asDoubleList
import secondOrNull

object Subtraction : LogicOperation {
    override val key: String = "-"

    override fun invoke(expression: Any?, data: Any?) = with(expression?.asDoubleList.orEmpty()) {
        when (size) {
            0 -> null
            1 -> firstOrNull()?.unaryMinus()
            else -> (firstOrNull() ?: 0.0) - (secondOrNull() ?: 0.0)
        }
    }
}
