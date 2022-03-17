package operations

interface LogicOperation : (Any?, Any?) -> Any? {
    val key: String
    val operation
        get() = key to this
}
