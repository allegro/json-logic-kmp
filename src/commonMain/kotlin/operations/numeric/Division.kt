package operations.numeric

import LogicOperation
import asDoubleList

object Division : LogicOperation {
    override val key: String = "/"

    override fun invoke(expression: Any?, data: Any?) = with(expression?.asDoubleList) {
        this?.getOrNull(1)
            .takeIf { it != 0.0 }
            ?.let { divisor ->
                (this?.firstOrNull() ?: 0.0) / divisor
            }
    }
}
