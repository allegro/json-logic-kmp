package operations.array

import operations.array.unwrap.EvaluatingUnwrapStrategy
import utils.secondOrNull
import kotlin.collections.Map

internal interface ArrayOperation : EvaluatingUnwrapStrategy {
    fun getMappingOperationOrNull(expression: List<Any?>) =
        expression.secondOrNull().takeIf { isExpression(it) } as? Map<String, Any>

    fun getOperationDefault(mappingOperation: Map<String, Any>?, expressionValues: List<Any?>) =
        if (mappingOperation == null) {
            expressionValues.secondOrNull()
        } else null
}
