internal class CommonJsonLogicEngine(private val evaluator: LogicEvaluator) : JsonLogicEngine {
    override fun evaluate(expression: Map<String, Any?>, data: Any?): JsonLogicResult =
        expression.takeIf {
            it.isNotEmpty()
        }?.let {
            safeEvaluate(expression, data)
        } ?: JsonLogicResult.Failure.EmptyExpression

    private fun safeEvaluate(expression: Map<String, Any?>, data: Any?) = runCatching {
        evaluator.evaluateLogic(expression, data)
    }.fold(
        onSuccess = ::toJsonLogicResult,
        onFailure = { JsonLogicResult.Failure.MissingOperation }
    )

    private fun toJsonLogicResult(evaluatedValue: Any?) = evaluatedValue?.let { notNullResult ->
        JsonLogicResult.Success(notNullResult.toNormalizedResult())
    } ?: JsonLogicResult.Failure.NullResult

    private fun Any.toNormalizedResult() =
        if (this is Double && this.mod(1.0) == 0.0) {
            this.toLong()
        } else this
}
