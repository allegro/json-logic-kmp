package unwrap

import LogicEvaluator

interface EvaluatingUnwrapper {
    fun unwrapDataByEvaluation(expression: List<Any?>, data: Any?, evaluator: LogicEvaluator) =
        (expression.firstOrNull().unwrapOperationData(data, evaluator) as? List<Any?>)

    @Suppress("UNCHECKED_CAST")
    private fun Any?.unwrapOperationData(data: Any?, evaluator: LogicEvaluator): Any? = when {
        this is List<*> -> map { it.unwrapOperationData(data, evaluator) }
        isExpression(this) -> evaluator.evaluateLogic(this as Map<String, Any?>, data)
        else -> this
    }

    fun isExpression(value: Any?) = (value as? Map<*, *>)?.let {
        it.isNotEmpty() && it.keys.all { key -> key is String }
    } ?: false
}

@Suppress("UNCHECKED_CAST")
fun EvaluatingUnwrapper.getMappingOperationOrNull(expression: List<Any?>) =
    expression.getOrNull(1).takeIf { isExpression(it) } as? Map<String, Any>
