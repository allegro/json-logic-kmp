interface JsonLogicEngine {
    fun evaluate(expression: Map<String, Any?>, data: Map<String, Any>): String

    companion object {
        val instance: JsonLogicEngine by lazy { CommonJsonLogicEngine() }
    }
}
