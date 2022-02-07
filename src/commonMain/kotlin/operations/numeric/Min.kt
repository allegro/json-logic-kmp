package operations.numeric

import LogicOperation
import asDoubleList
import asList

object Min : LogicOperation {
    override val key: String = "min"

    override fun invoke(expression: Any?, data: Any?): Any? {
        val (nullValues, doubleValues) = expression?.asDoubleList?.partition { it == null } ?: (null to null)
        return if(nullValues?.isNotEmpty() == true) {
            null
        } else {
            doubleValues?.filterNotNull()?.minOrNull()
        }
    }
}
