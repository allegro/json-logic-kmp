internal class CommonJsonLogicEngine : JsonLogicEngine {
    override fun evaluate(expression: String, data: Map<String, Any>): String {
        return "SUCCESS"
    }
}