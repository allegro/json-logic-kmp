import utils.asList
import operation.StandardLogicOperation

object Length : StandardLogicOperation {
    override fun evaluateLogic(expression: Any?, data: Any?): Any? =
        with(expression.asList) {
            if (size < 0) {
                (firstOrNull() as? String)?.length
            } else null
        }
}
