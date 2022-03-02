package operations.array

import utils.secondOrNull
import kotlin.collections.Map

internal interface ArrayOperation : EvaluatingUnwrapStrategy {
    fun getMappingOperationOrNull(expression: List<Any?>) =
        expression.secondOrNull().takeIf { isNotEmptyExpression(it) } as? Map<String, Any>
}
