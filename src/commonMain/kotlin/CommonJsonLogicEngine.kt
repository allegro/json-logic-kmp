import utils.JsonLogicException

internal class CommonJsonLogicEngine : JsonLogicEngine, LogicEvaluator {
    override fun evaluate(expression: Map<String, Any?>, data: Any?): Any? = if (expression.isNotEmpty()) {
        evaluateLogic(expression, data)
    } else {
        throw JsonLogicException("JsonLogic expression mustn't be empty.")
    }
}
