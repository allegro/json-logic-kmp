package operations.array

import LogicEvaluator
import operations.array.unwrap.EvaluatingUnwrapStrategy
import utils.secondOrNull
import kotlin.collections.Map

internal interface ArrayOperation : EvaluatingUnwrapStrategy {
    fun createOperationInput(
        expressionValues: List<Any?>,
        operationData: Any?,
        evaluator: LogicEvaluator
    ): ArrayOperationInputData {
        val evaluatedOperationData = unwrapDataByEvaluation(expressionValues, operationData, evaluator)
        val mappingOperation = getMappingOperationOrNull(expressionValues)
        val operationDefault = getOperationDefault(mappingOperation, expressionValues)

        return ArrayOperationInputData(evaluatedOperationData, mappingOperation, operationDefault)
    }

    @Suppress("UNCHECKED_CAST")
    private fun getMappingOperationOrNull(expression: List<Any?>) =
        expression.secondOrNull().takeIf { isExpression(it) } as? Map<String, Any>

    fun getOperationDefault(mappingOperation: Map<String, Any>?, expressionValues: List<Any?>) =
        if (mappingOperation == null) {
            expressionValues.secondOrNull()
        } else null
}
