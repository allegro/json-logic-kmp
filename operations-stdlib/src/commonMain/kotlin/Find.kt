import unwrap.EvaluatingUnwrapper
import utils.asList
import utils.secondOrNull

// TODO think about moving evaluating strategy to utils module
object Find : FunctionalLogicOperation, EvaluatingUnwrapper {
    override fun invoke(expression: Any?, data: Any?, evaluator: LogicEvaluator): Any? {
        return expression.asList.let { expressionValues ->
            val inputData = unwrapDataByEvaluation(expressionValues, data, evaluator)
            val predicateOperation = getMappingOperationOrNull(expressionValues)

            predicateOperation?.let {
                inputData?.find { evaluator.evaluateLogic(predicateOperation, it) == true }
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun getMappingOperationOrNull(expression: List<Any?>) =
        expression.secondOrNull().takeIf { isExpression(it) } as? Map<String, Any>
}
