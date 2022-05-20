import operation.StandardLogicOperation
import utils.asList

object Reverse : StandardLogicOperation {
    override fun evaluateLogic(expression: Any?, data: Any?): Any? {
        return when (val firstItem = expression.asList.firstOrNull()) {
            is String -> firstItem.reversed()
            is List<*> -> firstItem.reversed()
            else -> null
        }
    }
}
