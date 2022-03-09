internal class CommonJsonLogicEngine : JsonLogicEngine, LogicEvaluator {
    override fun evaluate(expression: Map<String, Any?>, data: Any?): JsonLogicResult =
        expression.takeIf {
            it.isNotEmpty()
        }?.let {
            nullSafeEvaluate(expression, data)
        } ?: JsonLogicResult.Failure("JsonLogic expression mustn't be empty.")

    private fun nullSafeEvaluate(expression: Map<String, Any?>, data: Any?) = runCatching {
        evaluateLogic(expression, data)
    }.fold(
        onSuccess = ::toJsonLogicResult,
        onFailure = { JsonLogicResult.Failure(it.message) }
    )

    private fun toJsonLogicResult(evaluatedValue: Any?) = evaluatedValue?.let { notNullResult ->
        JsonLogicResult.Success(notNullResult)
    } ?: JsonLogicResult.Failure("Evaluated expression has returned null.")
}
