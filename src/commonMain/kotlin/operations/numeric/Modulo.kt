package operations.numeric

import LogicOperation
import asDoubleList

object Modulo : LogicOperation {
    override val key: String = "%"

    override fun invoke(expression: Any?, data: Any?) = expression?.asDoubleList
        ?.takeIf { it.size > 1 }
        ?.let {
            it.first() % it[1]
        }
}
