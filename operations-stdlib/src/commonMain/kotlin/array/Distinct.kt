package array

import operation.StandardLogicOperation
import utils.asList
import utils.isSingleNullList

object Distinct : StandardLogicOperation {
    override fun evaluateLogic(expression: Any?, data: Any?): Any? {
        return when (val firstItem = expression.asList.firstOrNull()) {
            is List<*> -> firstItem.distinct()
            firstItem.isSingleNullList() -> null
            else -> null
        }
    }
}
