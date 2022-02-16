package operations.numeric.unwrap

internal interface UnwrapStrategy {
    fun unwrapValues(wrappedValue: Any?): List<Any?>
}
