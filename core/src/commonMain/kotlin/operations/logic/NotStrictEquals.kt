package operations.logic

import operation.StandardLogicOperation

internal object NotStrictEquals : StandardLogicOperation, StrictEqualsOperation {
    override fun evaluateLogic(expression: Any?, data: Any?): Boolean =
        !compare(expression) { first, second -> first == second }
}

