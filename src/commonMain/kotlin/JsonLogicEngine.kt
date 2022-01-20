interface JsonLogicEngine {
    fun evaluate(expression: String, data: Map<String, Any>): String

    companion object {
        val instance: JsonLogicEngine by lazy { CommonJsonLogicEngine() }
    }
}
