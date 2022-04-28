package operations.array

import LogicEvaluator
import unwrap.EvaluatingUnwrapper
import utils.getMappingOperationOrNull
import utils.secondOrNull
import kotlin.collections.Map

internal interface ArrayOperation : EvaluatingUnwrapper {
    fun createOperationInput(
        expressionValues: List<Any?>,
        operationData: Any?,
        evaluator: LogicEvaluator
    ): ArrayOperationInputData {
        val evaluatedOperationData = unwrapDataByEvaluation(expressionValues, operationData, evaluator)
        val mappingOperation = expressionValues.getMappingOperationOrNull()
        val operationDefault = getOperationDefault(mappingOperation, expressionValues)

        return ArrayOperationInputData(evaluatedOperationData, mappingOperation, operationDefault)
    }

    fun getOperationDefault(mappingOperation: Map<String, Any>?, expressionValues: List<Any?>) =
        if (mappingOperation == null) {
            expressionValues.secondOrNull()
        } else null
}
