package operations.array.unwrap

import LogicEvaluator
import LogicOperations

internal interface EvaluatingUnwrapStrategy : LogicEvaluator {
    fun unwrapOperationData(expression: List<Any?>, data: Any?, operations: LogicOperations) =
        (expression.firstOrNull().unwrapOperationData(data, operations) as? List<*>)

    @Suppress("UNCHECKED_CAST")
    private fun Any?.unwrapOperationData(data: Any?, operations: LogicOperations): Any? = when {
        this is List<*> -> map { it.unwrapOperationData(data, operations) }
        isExpression(this) -> evaluateLogic(this as Map<String, Any?>, data, operations)
        else -> this
    }

    fun isExpression(value: Any?) = (value as? Map<*, *>)?.let {
        it.isNotEmpty() && it.keys.all { key -> key is String }
    } ?: false
}
