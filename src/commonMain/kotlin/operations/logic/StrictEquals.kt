package operations.logic

import operations.StandardLogicOperation

internal object StrictEquals : StandardLogicOperation, StrictEqualsOperation {
    override fun invoke(
        expression: Any?,
        data: Any?
    ): Boolean = compare(expression) { first, second -> first == second }
}


