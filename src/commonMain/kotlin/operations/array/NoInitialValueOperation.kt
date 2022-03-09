package operations.array

import utils.asList
import kotlin.collections.Map

internal interface NoInitialValueOperation : ArrayOperation {
    fun invokeArrayOperation(
        expression: Any?,
        data: Any?,
        arrayOperation: (List<Any?>, Map<String, Any>?, Any?) -> Any?
    ) = expression.asList.let { expressionValues ->
        val evaluatedOperationData = unwrapOperationData(expressionValues, data)
        val mappingOperation = getMappingOperationOrNull(expressionValues)
        val operationDefault = getOperationDefault(mappingOperation, expressionValues)

        arrayOperation(evaluatedOperationData, mappingOperation, operationDefault)
    }

    override fun unwrapOperationData(expression: List<Any?>, data: Any?) =
        super.unwrapOperationData(expression, data).orEmpty()
}
