package operations.array

import operations.LogicOperation
import kotlin.collections.Map

internal object None : LogicOperation, OccurrenceCheckOperation {
    override val key: String = "none"

    override fun invoke(expression: Any?, data: Any?): Any? =
        invokeArrayOperation(expression, data) { operationData, operation, default ->
            evaluateOrDefault(operationData, operation, default, ::none)
        }

    override fun getOperationDefault(mappingOperation: Map<String, Any>?, expressionValues: List<Any?>) = true

    private fun none(
        operationData: List<Any?>,
        mappingOperation: Map<String, Any>,
        operationDefault: Any?
    ): Any? {
        operationData.forEach { dataValue ->
            if (unwrapValueAsBoolean(evaluateLogic(mappingOperation, dataValue))) {
                return@none false
            }
        }
        return operationDefault
    }
}
