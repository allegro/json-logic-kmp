interface JsonLogicEngine {
    fun evaluate(expression: Map<String, Any?>, data: Any?): Any?

    companion object {
        val instance: JsonLogicEngine by lazy { CommonJsonLogicEngine() }
    }
}
