package operations.numeric

import LogicOperation
import asDoubleList
import asList
import operations.numeric.unwrap.LenientUnwrapStrategy
import secondOrNull

internal object Subtraction : LogicOperation, LenientUnwrapStrategy {
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
}
