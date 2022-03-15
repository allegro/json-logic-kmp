sealed class TestInput(
    open val expression: Map<String, Any?>,
    open val data: Any?,
) {
    data class Successful(
        override val expression: Map<String, Any?>,
        override val data: Any? = emptyMap<String, Any>(),
        val resultValue: Any?
    ) : TestInput(expression, data)

    data class Unsuccessful(
        override val expression: Map<String, Any?>,
        override val data: Any? = emptyMap<String, Any>()
    ) : TestInput(expression, data)
}
