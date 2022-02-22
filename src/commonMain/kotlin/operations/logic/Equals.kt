package operations.logic

import operations.LogicOperation

internal object Equals : LogicOperation, EqualsOperation {
    override val key: String = "=="

    override fun invoke(
        expression: Any?,
        data: Any?
    ): Boolean = safeCompare(expression) { first, second -> first == second }
}
