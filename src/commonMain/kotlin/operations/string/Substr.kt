package operations.string

import LogicOperation
import asDoubleList

object Substr : LogicOperation {
    override val key: String = "substr"

    override fun invoke(expression: Any?, data: Any?) = with(expression?.asDoubleList) {
        this?.getOrNull(1)
            .takeIf { it != 0.0 }
            ?.let { divisor ->
                (this?.firstOrNull() ?: 0.0) / divisor
        }
    }
}
