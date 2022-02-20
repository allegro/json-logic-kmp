package operations.logic

import operations.LogicOperation
import utils.asList

internal object NotStrictEquals : LogicOperation, StrictComparingOperation {
    override val key: String = "!=="

    override fun invoke(expression: Any?, data: Any?): Boolean = !sizeSafeCompare(expression) { first, second -> first == second }
}

