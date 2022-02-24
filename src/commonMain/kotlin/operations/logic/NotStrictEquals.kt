package operations.logic

import operations.LogicOperation

internal object NotStrictEquals : LogicOperation, StrictEqualsOperation {
    override val key: String = "!=="

    override fun invoke(expression: Any?, data: Any?): Boolean =
        !compare(expression) { first, second -> first == second }
}

