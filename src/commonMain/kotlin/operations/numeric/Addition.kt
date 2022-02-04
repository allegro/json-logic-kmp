package operations.numeric

import LogicOperation
import asDoubleList

object Addition : LogicOperation {
    override val key: String = "+"

    override fun invoke(expression: Any?, data: Any?) = expression?.asDoubleList?.sum()
}
