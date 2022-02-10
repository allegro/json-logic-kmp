package operations.numeric

import LogicOperation
import asDoubleList

object Addition : LogicOperation {
    override val key: String = "+"

    override fun invoke(expression: Any?, data: Any?): Any? {
        val (nullValues, doubleValues) = expression?.asDoubleList?.partition { it == null } ?: (null to null)
        return if (nullValues?.isEmpty() == true) {
            doubleValues?.filterNotNull()?.sum()
        } else null
    }
}
