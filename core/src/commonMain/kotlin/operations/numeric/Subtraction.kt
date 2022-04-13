package operations.numeric

import StandardLogicOperation
import operations.numeric.unwrap.LenientUnwrapStrategy

internal object Subtraction : StandardLogicOperation, LenientUnwrapStrategy {
    override fun invoke(expression: Any?, data: Any?) = with(unwrapValueAsDouble(expression)) {
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
