import operation.StandardLogicOperation

actual object CurrentTimeMillis : StandardLogicOperation {
    actual override fun evaluateLogic(expression: Any?, data: Any?): Any = System.currentTimeMillis()
}
