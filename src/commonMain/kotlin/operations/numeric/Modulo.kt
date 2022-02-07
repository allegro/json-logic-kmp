package operations.numeric

import LogicOperation
import asDoubleList

object Modulo : LogicOperation {
    override val key: String = "%"

    override fun invoke(expression: Any?, data: Any?) = expression?.asDoubleList
        ?.filterNotNull()
        ?.takeIf { it.size > 1 && it[1] != 0.0 }
        ?.let {
            it.first() % it[1]
        }
}
