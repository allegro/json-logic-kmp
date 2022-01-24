internal interface LogicExpression : (Any?, Any?) -> Any? {
    val key: String
    override fun invoke(expression: Any?, data: Any?): Any?

    val LogicExpression.operation
        get() = key to this
}
