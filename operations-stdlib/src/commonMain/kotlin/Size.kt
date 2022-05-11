import operation.StandardLogicOperation
import utils.asList
import utils.isSingleNullList

object Size : StandardLogicOperation {
    override fun evaluateLogic(expression: Any?, data: Any?): Any? {
        return when (val firstItem = expression.asList.firstOrNull()) {
            firstItem.isSingleNullList() -> null
            is Map<*, *> -> firstItem.size
            is List<*> -> firstItem.size
            else -> null
        }
    }
}
