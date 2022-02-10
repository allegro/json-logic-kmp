package operations.numeric

import LogicOperation
import asDoubleList
import secondOrNull

object Division : LogicOperation {
    override val key: String = "/"

    override fun invoke(expression: Any?, data: Any?) = with(expression?.asDoubleList) {
        this?.secondOrNull()
            .takeIf { it != 0.0 }
            ?.let { divisor ->
                (this?.firstOrNull() ?: 0.0) / divisor
            }
    }
}
