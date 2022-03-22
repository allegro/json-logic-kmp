package operations.array.occurence

import evaluation.LogicEvaluator
import operations.array.NoInitialValueOperation
import operations.array.ArrayOperationInputData
import operations.logic.unwrap.TruthyUnwrapStrategy

internal interface OccurrenceCheckOperation : NoInitialValueOperation, TruthyUnwrapStrategy {
    fun check(data: OccurrenceCheckInputData, evaluator: LogicEvaluator): Any?

    fun checkOccurrence(expression: Any?, data: Any?, evaluator: LogicEvaluator) =
        invokeArrayOperation(expression, data, evaluator) { input, logicEvaluator ->
            evaluateOrDefault(input, logicEvaluator, ::check)
        }

    private fun evaluateOrDefault(
        operationInput: ArrayOperationInputData,
        evaluator: LogicEvaluator,
        arrayOperation: (OccurrenceCheckInputData, LogicEvaluator) -> Any?
    ) = operationInput.toOccurrenceCheckInput()?.let {
        arrayOperation(it, evaluator)
    } ?: operationInput.operationDefault

    override fun getOperationDefault(mappingOperation: Map<String, Any>?, expressionValues: List<Any?>) = false

    private fun ArrayOperationInputData.toOccurrenceCheckInput() =
        if (mappingOperation != null && operationData != null && operationData.isNotEmpty()) {
            OccurrenceCheckInputData(operationData, mappingOperation, operationDefault)
        } else null
}
