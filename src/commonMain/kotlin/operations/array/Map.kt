package operations.array

import operations.LogicOperation
import utils.asList
import utils.secondOrNull
import kotlin.collections.Map as MapCollection

internal object Map : LogicOperation, ArrayOperation {
    override val key: String = "map"

    // TODO add test for size < 2
    override fun invoke(expression: Any?, data: Any?): Any? =
        expression.asList.takeIf { it.size >= 2 }?.let { expressionValues ->
            val evaluatedOperationData = unwrapOperationData(expressionValues, data)
            val mappingOperation = getMappingOperationOrNull(expressionValues)

            mapOrEmptyList(evaluatedOperationData, mappingOperation, expressionValues.secondOrNull())
        }

    private fun mapOrEmptyList(
        operationData: List<Any?>,
        mappingOperation: MapCollection<String, Any>?,
        operationDefault: Any?
    ) = operationData.map { evaluatedValue ->
        mappingOperation?.let { operation -> evaluate(operation, evaluatedValue) } ?: operationDefault
    }

    override fun unwrapOperationData(expression: List<Any?>, data: Any?) =
        super.unwrapOperationData(expression, data).orEmpty()
}
