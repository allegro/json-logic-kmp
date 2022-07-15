import operation.StandardLogicOperation

object Reverse : StandardLogicOperation {
    override fun evaluateLogic(expression: Any?, data: Any?): Any? {
        return when (expression) {
            is String -> expression.reversed()
            is List<*> -> expression.reversed()
            else -> null

        }
    }
}
