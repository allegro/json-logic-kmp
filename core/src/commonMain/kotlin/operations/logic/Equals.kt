package operations.logic

import operation.StandardLogicOperation

internal object Equals : StandardLogicOperation, EqualsOperation {
    override fun evaluateLogic(
        expression: Any?,
        data: Any?
    ): Boolean = compare(expression) { first, second -> first == second }
}
