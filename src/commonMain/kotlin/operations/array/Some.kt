package operations.array

import operations.LogicOperation
import kotlin.collections.Map

internal object Some : LogicOperation, OccurrenceCheckOperation {
    override val key: String = "some"

    override fun invoke(expression: Any?, data: Any?): Any? =
        invokeArrayOperation(expression, data) { operationData, operation, default ->
            evaluateOrDefault(operationData, operation, default, ::some)
        }

    override fun getOperationDefault(mappingOperation: Map<String, Any>?, expressionValues: List<Any?>) = false

    private fun some(operationData: List<Any?>, mappingOperation: Map<String, Any>, operationDefault: Any?): Any? {
        operationData.forEach { dataValue ->
            if (unwrapValueAsBoolean(evaluateLogic(mappingOperation, dataValue))) {
                return@some true
            }
        }
        return operationDefault
    }
}

