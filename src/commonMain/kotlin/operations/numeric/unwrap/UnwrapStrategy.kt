package operations.numeric.unwrap

internal interface UnwrapStrategy {
    fun Any?.unwrapValues(): List<Any?>
}
