import utils.JsonLogicException
import utils.asList

// zrobic z tego klase z akcjami i to przekazywac pomiedzy funkcjami,
// dodatkowo default instancje tego, zeby ludzie piszacy functional operations mogli tez tego uzywac zamiast wplatac caly silnik
internal interface LogicEvaluator {
    fun evaluateLogic(expression: Map<String, Any?>, data: Any?, operations: LogicOperations): Any? =
        executeExpression(expression, data, operations)
//    val standardOperations: Map<String, (Any?, Any?) -> Any?>
//    val selfEvaluatingOperations: Map<String, (Any?, Any?) -> Any?>

    private fun executeExpression(logic: Any?, data: Any?, operations: LogicOperations): Any? {
        return when {
            logic is List<*> -> logic.map { executeExpression(it, data, operations) }
            logic !is Map<*, *> -> logic
            logic.isEmpty() -> data
            else -> executeOperation(logic, data, operations)
        }
    }

    private fun executeOperation(logic: Map<*, *>, data: Any?, operations: LogicOperations): Any? {
        val operator = logic.keys.firstOrNull()
        val values = logic[operator]
        return if (operations.functionalOperations.keys.contains(operator)) {
            operations.functionalOperations[operator]?.invoke(values.asList, data)
        } else {
            operations.standardOperations.getOperation(operator).invoke(when (values) {
                is List<*> -> values.map { executeExpression(it, data, operations) }
                is Map<*, *> -> executeExpression(values, data, operations)
                else -> executeExpression(listOf(values), data, operations)
            }.asList, data)
        }
    }

    private fun Map<String, (Any?, Any?) -> Any?>.getOperation(operator: Any?) =
        get(operator) ?: throw JsonLogicException("Operation $operator not found.")
}
