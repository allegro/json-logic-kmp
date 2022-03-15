package operations.array.unwrap

import LogicEvaluator

internal interface EvaluatingUnwrapStrategy : LogicEvaluator {
    fun unwrapOperationData(expression: List<Any?>, data: Any?) =
        (expression.firstOrNull().unwrapOperationData(data) as? List<*>)

    @Suppress("UNCHECKED_CAST")
    private fun Any?.unwrapOperationData(data: Any?): Any? = when {
        this is List<*> -> map { it.unwrapOperationData(data) }
        isExpression(this) -> evaluateLogic(this as Map<String, Any?>, data)
        else -> this
    }

    fun isExpression(value: Any?) = (value as? Map<*, *>)?.let {
        it.isNotEmpty() && it.keys.all { key -> key is String }
    } ?: false
}
