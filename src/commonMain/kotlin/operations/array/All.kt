package operations.array

import operations.LogicOperation
import operations.logic.unwrap.TruthyUnwrapStrategy
import utils.asList
import kotlin.collections.Map

internal object All : LogicOperation, NoInitialValueOperation, TruthyUnwrapStrategy {
    override val key: String = "all"

    override fun invoke(expression: Any?, data: Any?): Any? {
        expression.asList.let { expressionValues ->
            val evaluatedOperationData = unwrapOperationData(expressionValues, data)
            val mappingOperation = getMappingOperationOrNull(expressionValues)
            return if (evaluatedOperationData.isEmpty()) {
                false
            } else {
                all(evaluatedOperationData, mappingOperation)
            }
        }
    }

    private fun all(
        operationData: List<Any?>,
        mappingOperation: Map<String, Any>?,
    ): Boolean {
        mappingOperation?.let { operation ->
            operationData.forEach { dataValue ->
                if (unwrapValueAsBoolean(evaluateLogic(operation, dataValue)).not()) {
                    return@all false
                }
            }
        } ?: return false
        return true
    }
}

