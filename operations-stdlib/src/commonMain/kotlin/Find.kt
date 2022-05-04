import operation.FunctionalLogicOperation
import unwrap.EvaluatingUnwrapper
import utils.asList
import utils.getMappingOperationOrNull

object Find : FunctionalLogicOperation, EvaluatingUnwrapper {
    override fun evaluateLogic(expression: Any?, data: Any?, evaluator: LogicEvaluator): Any? {
        return expression.asList.let { expressionValues ->
            val inputData = unwrapDataByEvaluation(expressionValues, data, evaluator)
            val predicateOperation = expressionValues.getMappingOperationOrNull()

            predicateOperation?.let {
                inputData?.find { evaluator.evaluateLogic(predicateOperation, it) == true }
            }
        }
    }
}
