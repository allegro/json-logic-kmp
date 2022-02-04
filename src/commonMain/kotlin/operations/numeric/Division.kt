package operations.numeric

import LogicOperation
import asDoubleList
import asList

object Division : LogicOperation {
    override val key: String = "/"

    override fun invoke(expression: Any?, data: Any?) = with(expression?.asDoubleList) {
        (this?.firstOrNull() ?: 0.0) / (this?.getOrNull(1) ?: 1.0)
    }
}
