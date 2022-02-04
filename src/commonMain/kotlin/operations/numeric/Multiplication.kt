package operations.numeric

import LogicOperation
import asDoubleList
import asList

object Multiplication : LogicOperation {
    override val key: String = "*"

    override fun invoke(expression: Any?, data: Any?) = expression?.asDoubleList?.reduce { sum, cur -> sum * cur }
}
