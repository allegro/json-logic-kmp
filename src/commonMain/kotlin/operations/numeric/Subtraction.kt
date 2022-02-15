package operations.numeric

import LogicOperation
import asDoubleList
import operations.numeric.unwrap.LenientUnwrapStrategy

internal object Subtraction : LogicOperation, LenientUnwrapStrategy {
    override val key: String = "-"

    override fun invoke(expression: Any?, data: Any?) = with(unwrapValues(expression)) {
        when (size) {
            0 -> null
            1 -> first()?.unaryMinus()
            else -> minusOrNull(first(), get(1))
        }
    }

    private fun minusOrNull(first: Double?, second: Double?) = if (first != null && second != null) {
        first - second
    } else null
}
