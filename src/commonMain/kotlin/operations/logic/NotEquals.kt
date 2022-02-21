package operations.logic

import operations.LogicOperation

internal object NotEquals : LogicOperation, EqualsOperation {
    override val key: String = "!="

    override fun invoke(expression: Any?, data: Any?): Boolean = !sizeSafeCompare(expression) { first, second -> first == second }
}
