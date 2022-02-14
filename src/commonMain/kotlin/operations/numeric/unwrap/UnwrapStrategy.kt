package operations.numeric.unwrap

interface UnwrapStrategy {
    fun Any?.unwrapValues(): List<Any?>
}
