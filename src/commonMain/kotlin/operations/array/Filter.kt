package operations.array

import operations.LogicOperation
import operations.logic.unwrap.TruthyUnwrapStrategy
import utils.asList
import utils.secondOrNull
import kotlin.collections.Map as MapCollection

internal object Filter : LogicOperation, ArrayOperation, TruthyUnwrapStrategy {
    override val key: String = "filter"

    // TODO add test for size < 2
    override fun invoke(expression: Any?, data: Any?): Any? =
        expression.asList.takeIf { it.size >= 2 }?.let { expressionValues ->
            val evaluatedOperationData = unwrapOperationData(expressionValues, data)
            val mappingOperation = getMappingOperationOrNull(expressionValues)

            filterOrEmptyList(evaluatedOperationData, mappingOperation, expressionValues.secondOrNull())
        }

    private fun filterOrEmptyList(
        operationData: List<Any?>,
        mappingOperation: MapCollection<String, Any>?,
        operationDefault: Any?
    ) = operationData.filter { evaluatedValue ->
        unwrapValueAsBoolean((mappingOperation?.let { operation ->
            evaluate(operation, evaluatedValue)
        } ?: operationDefault))
    }

    override fun unwrapOperationData(expression: List<Any?>, data: Any?) =
        super.unwrapOperationData(expression, data).orEmpty()
}
