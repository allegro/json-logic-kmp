package operations.array

import operations.LogicOperation
import kotlin.collections.Map

internal object All : LogicOperation, OccurrenceCheckOperation {
    override val key: String = "all"

    override fun invoke(expression: Any?, data: Any?): Any? =
        invokeArrayOperation(expression, data) { operationData, operation, default ->
            evaluateOrDefault(operationData, operation, default, ::all)
        }

    private fun all(
        operationData: List<Any?>,
        mappingOperation: Map<String, Any>,
        operationDefault: Any?
    ): Any? {
        operationData.forEach { dataValue ->
            if (unwrapValueAsBoolean(evaluateLogic(mappingOperation, dataValue)).not()) {
                return@all operationDefault
            }
        }
        return true
    }
}


