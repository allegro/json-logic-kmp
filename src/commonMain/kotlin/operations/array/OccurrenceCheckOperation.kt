package operations.array

import LogicEvaluator
import operations.logic.unwrap.TruthyUnwrapStrategy
import kotlin.collections.Map

internal interface OccurrenceCheckOperation : NoInitialValueOperation, TruthyUnwrapStrategy {
    fun check(
        data: OccurenceCheckData,
        evaluator: LogicEvaluator
    ): Any?

    fun checkOccurrence(expression: Any?, data: Any?, evaluator: LogicEvaluator) =
        invokeArrayOperation(expression, data, evaluator) { operationData, operation, default, evaluator ->
            evaluateOrDefault(operationData, operation, default, evaluator, ::check)
        }

    // TODO too long parameters list
    private fun evaluateOrDefault(
        operationData: List<Any?>,
        mappingOperation: Map<String, Any>?,
        operationDefault: Any?,
        evaluator: LogicEvaluator,
        arrayOperation: (OccurenceCheckData, LogicEvaluator) -> Any?
    ): Any? {
        return if (mappingOperation != null && operationData.isNotEmpty()) {
            arrayOperation(OccurenceCheckData(operationData, mappingOperation, operationDefault), evaluator)
        } else operationDefault
    }

    override fun getOperationDefault(mappingOperation: Map<String, Any>?, expressionValues: List<Any?>) = false
}

internal data class OccurenceCheckData(
    val operationData: List<Any?>,
    val mappingOperation: Map<String, Any>,
    val operationDefault: Any?
)
