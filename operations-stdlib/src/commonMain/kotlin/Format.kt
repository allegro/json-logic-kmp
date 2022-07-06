import operation.StandardLogicOperation

expect object Format : StandardLogicOperation {
    override fun evaluateLogic(expression: Any?, data: Any?): Any
}
