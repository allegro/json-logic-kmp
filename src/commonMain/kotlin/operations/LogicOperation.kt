package operations

internal interface LogicOperation : (Any?, Any?) -> Any? {
    val key: String
    val operation
        get() = key to this

    override fun invoke(expression: Any?, data: Any?): Any?
}
