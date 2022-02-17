package operations.logic

import operations.LogicOperation
import utils.asList

internal object StrictEquals : LogicOperation, StrictComparingOperation {
    override val key: String = "==="

    override fun invoke(expression: Any?, data: Any?): Boolean = with(expression.asList) {
        sizeSafeCompare(values = this) { first, second -> first == second }
    }
}


