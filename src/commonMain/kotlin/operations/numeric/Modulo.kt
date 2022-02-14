package operations.numeric

import LogicOperation
import operations.numeric.unwrap.LenientUnwrapStrategy

internal object Modulo : LogicOperation, LenientUnwrapStrategy {
    override val key: String = "%"

    override fun invoke(expression: Any?, data: Any?) =
        expression.unwrapValues().takeIf { it.size >= 2 }?.let {
            val second = it[1]
            val first = it.first()
            if (first != null && second != null && second != 0.0) {
                first % second
            } else null
        }
}
