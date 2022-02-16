package operations

internal interface UnwrapStrategy<T> {
    fun unwrapValue(wrappedValue: Any?): T
}
