package operations.logic

import operations.StandardLogicOperation

internal object Equals : StandardLogicOperation, EqualsOperation {
    override fun invoke(
        expression: Any?,
        data: Any?
    ): Boolean = compare(expression) { first, second -> first == second }
}
