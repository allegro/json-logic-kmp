package operations.array

import operations.LogicOperation
import operations.logic.unwrap.TruthyUnwrapStrategy
import utils.asList
import kotlin.collections.Map

internal object None : LogicOperation, NoInitialValueOperation, TruthyUnwrapStrategy {
    override val key: String = "none"

    override fun invoke(expression: Any?, data: Any?): Any? {
        expression.asList.let { expressionValues ->
            val evaluatedOperationData = unwrapOperationData(expressionValues, data)
            val mappingOperation = getMappingOperationOrNull(expressionValues)
            return if (mappingOperation != null && evaluatedOperationData.isNotEmpty()) {
                none(evaluatedOperationData, mappingOperation)
            } else true
        }
    }

    private fun none(
        operationData: List<Any?>,
        mappingOperation: Map<String, Any>,
    ): Boolean {
        operationData.forEach { dataValue ->
            if (unwrapValueAsBoolean(evaluateLogic(mappingOperation, dataValue))) {
                return@none false
            }
        }
        return true
    }
}
