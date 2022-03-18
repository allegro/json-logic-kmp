internal class CommonJsonLogicEngine(private val evaluator: LogicEvaluator) : JsonLogicEngine {
    override fun evaluate(expression: Map<String, Any?>, data: Any?): JsonLogicResult =
        expression.takeIf {
            it.isNotEmpty()
        }?.let {
            safeEvaluate(expression, data)
        } ?: JsonLogicResult.Failure("JsonLogic expression mustn't be empty.")

    private fun safeEvaluate(expression: Map<String, Any?>, data: Any?) = runCatching {
        evaluator.evaluateLogic(expression, data)
    }.fold(
        onSuccess = ::toJsonLogicResult,
        onFailure = { JsonLogicResult.Failure(it.message) }
    )

    private fun toJsonLogicResult(evaluatedValue: Any?) = evaluatedValue?.let { notNullResult ->
        JsonLogicResult.Success(notNullResult)
    } ?: JsonLogicResult.Failure("Evaluated expression has returned null.")
}

// pierwszy pomysl to podczas buildowania wrzucic wszystko do common enginea i niech on przy kazdym evaluateLogic przekazuje dostepne operacje

// TODO
// wymyslec sposob, zeby do raz zainicjalizowanego zestawu operacji kazdy impl of LogicEvaluator mial do nich dostep
