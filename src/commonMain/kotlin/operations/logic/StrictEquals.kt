package operations.logic

import operations.LogicOperation
import utils.asList

internal object StrictEquals : LogicOperation, StrictComparingOperation {
    override val key: String = "==="

    override fun invoke(expression: Any?, data: Any?): Boolean = with(expression.asList) {
        compareListOfTwoOrDefault(values = this, default = false) { first, second -> first == second }
    }
}


