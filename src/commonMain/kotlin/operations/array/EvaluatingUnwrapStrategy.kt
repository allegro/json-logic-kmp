package operations.array

import JsonLogicEngine
import kotlin.collections.Map

internal interface EvaluatingUnwrapStrategy {
    fun evaluate(expression: Map<String, Any?>, data: Any?) = JsonLogicEngine.instance.evaluate(expression, data)

    fun evaluateOperationData(expression: List<Any?>, data: Any?) =
        (expression.firstOrNull().unwrapOperationData(data) as? List<*>)

    private fun Any?.unwrapOperationData(data: Any?): Any? = when {
        this is List<*> -> map { it.unwrapOperationData(data) }
        isNotEmptyExpression(this) -> evaluate(this as Map<String, Any?>, data)
        else -> this
    }

    fun isNotEmptyExpression(value: Any?) = (value as? Map<*, *>)?.let {
        it.isNotEmpty() && it.keys.all { key -> key is String }
    } ?: false
}
