package operations.array

import LogicOperations
import utils.asList
import kotlin.collections.Map

internal interface NoInitialValueOperation : ArrayOperation {
    fun invokeArrayOperation(
        expression: Any?,
        operationData: Any?,
        operations: LogicOperations,
        arrayOperation: (List<Any?>, Map<String, Any>?, Any?) -> Any?
    ) = expression.asList.let { expressionValues ->
        val evaluatedOperationData = unwrapOperationData(expressionValues, operationData, operations)
        val mappingOperation = getMappingOperationOrNull(expressionValues)
        val operationDefault = getOperationDefault(mappingOperation, expressionValues)

        arrayOperation(evaluatedOperationData, mappingOperation, operationDefault)
    }

    override fun unwrapOperationData(expression: List<Any?>, data: Any?, operations: LogicOperations) =
        super.unwrapOperationData(expression, data, operations).orEmpty()
}
