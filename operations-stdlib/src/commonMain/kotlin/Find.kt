import unwrap.EvaluatingUnwrapper
import unwrap.getMappingOperationOrNull
import utils.asList

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
}
