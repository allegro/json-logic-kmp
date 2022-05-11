import utils.asList
import operation.StandardLogicOperation

object Length : StandardLogicOperation {
    override fun evaluateLogic(expression: Any?, data: Any?): Any? =
        if (expression.asList.size > 1) null
        else {
            val element = expression.asList.firstOrNull()
            if (element is String) element.toString().length
            else null
        }
}
