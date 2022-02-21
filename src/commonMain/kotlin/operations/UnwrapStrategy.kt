package operations

internal interface UnwrapStrategy<T> {
    fun unwrapValues(wrappedValue: Any?): T
}
