package operations.array

import LogicEvaluator
import LogicOperations
import utils.asList
import kotlin.collections.Map

internal interface NoInitialValueOperation : ArrayOperation {
    fun invokeArrayOperation(
        expression: Any?,
        operationData: Any?,
        evaluator: LogicEvaluator,
        arrayOperation: (List<Any?>, Map<String, Any>?, Any?, LogicEvaluator) -> Any?
    ) = expression.asList.let { expressionValues ->
        val evaluatedOperationData = unwrapOperationData(expressionValues, operationData, evaluator)
        val mappingOperation = getMappingOperationOrNull(expressionValues)
        val operationDefault = getOperationDefault(mappingOperation, expressionValues)

        arrayOperation(evaluatedOperationData, mappingOperation, operationDefault, evaluator)
    }

    override fun unwrapOperationData(expression: List<Any?>, data: Any?, evaluator: LogicEvaluator) =
        super.unwrapOperationData(expression, data, evaluator).orEmpty()
}
