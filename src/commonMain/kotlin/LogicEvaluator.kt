import utils.JsonLogicException
import utils.asList

internal interface LogicEvaluator {
    fun evaluateLogic(expression: Map<String, Any?>, data: Any?): Any? = executeExpression(expression, data)
//    val standardOperations: Map<String, (Any?, Any?) -> Any?>
//    val selfEvaluatingOperations: Map<String, (Any?, Any?) -> Any?>

    private fun executeExpression(logic: Any?, data: Any?): Any? {
        return when {
            logic is List<*> -> logic.map { executeExpression(it, data) }
            logic !is Map<*, *> -> logic
            logic.isEmpty() -> data
            else -> executeOperation(logic, data)
        }
    }

    private fun executeOperation(logic: Map<*, *>, data: Any?): Any? {
        val operator = logic.keys.firstOrNull()
        val values = logic[operator]
        return if (selfEvaluatingOperations.keys.contains(operator)) {
            selfEvaluatingOperations[operator]?.invoke(values.asList, data)
        } else {
            standardOperations.getOperation(operator).invoke(when (values) {
                is List<*> -> values.map { executeExpression(it, data) }
                is Map<*, *> -> executeExpression(values, data)
                else -> executeExpression(listOf(values), data)
            }.asList, data)
        }
    }

    private fun Map<String, (Any?, Any?) -> Any?>.getOperation(operator: Any?) =
        get(operator) ?: throw JsonLogicException("Operation $operator not found.")
}
