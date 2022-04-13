package operations.logic

import StandardLogicOperation

internal object NotEquals : StandardLogicOperation, EqualsOperation {
    override fun invoke(
        expression: Any?,
        data: Any?
    ): Boolean = !compare(expression) { first, second -> first == second }
}
