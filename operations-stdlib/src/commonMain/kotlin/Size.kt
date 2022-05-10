object Size : StandardLogicOperation {
    override fun invoke(expression: Any?, data: Any?): Any? {
        return when (expression) {
            is Map<*, *> -> expression.size
            is List<*> -> expression.size
            else -> null
        }
    }
}
