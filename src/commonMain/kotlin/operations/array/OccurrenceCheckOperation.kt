package operations.array

import LogicOperations
import operations.logic.unwrap.TruthyUnwrapStrategy
import kotlin.collections.Map

internal interface OccurrenceCheckOperation : NoInitialValueOperation, TruthyUnwrapStrategy {
    fun check(
        operationData: List<Any?>,
        mappingOperation: Map<String, Any>,
        operationDefault: Any?
    ): Any?

    fun checkOccurrence(expression: Any?, data: Any?, operations: LogicOperations) =
        invokeArrayOperation(expression, data, operations) { operationData, operation, default ->
            evaluateOrDefault(operationData, operation, default, ::check)
        }

    private fun evaluateOrDefault(
        operationData: List<Any?>,
        mappingOperation: Map<String, Any>?,
        operationDefault: Any?,
        arrayOperation: (List<Any?>, Map<String, Any>, Any?) -> Any?
    ): Any? {
        return if (mappingOperation != null && operationData.isNotEmpty()) {
            arrayOperation(operationData, mappingOperation, operationDefault)
        } else operationDefault
    }

    override fun getOperationDefault(mappingOperation: Map<String, Any>?, expressionValues: List<Any?>) = false
}
