package evaluation

import JsonLogicException
import LogicEvaluator
import operation.StandardLogicOperation
import utils.asList

internal class CommonLogicEvaluator(private val operations: LogicOperations) : LogicEvaluator {
    override fun evaluateLogic(expression: Map<String, Any?>, data: Any?): Any? =
        executeExpression(expression, data)

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
        return if (operations.functionalOperations.keys.contains(operator)) {
            operations.functionalOperations[operator]?.evaluateLogic(values, data, this)
        } else {
            operations.standardOperations.getOperation(operator).evaluateLogic(when (values) {
                is List<*> -> values.map { executeExpression(it, data) }
                is Map<*, *> -> executeExpression(values, data)
                else -> executeExpression(values, data)
            }, data)
        }
    }

    private fun Map<String, StandardLogicOperation>.getOperation(operator: Any?) =
        get(operator) ?: throw JsonLogicException("Operation $operator not found.")
}
