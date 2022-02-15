package operations.logic

import operations.LogicOperation
import utils.asList
import operations.numeric.compare.StrictComparingOperation

internal object NotStrictEquals : LogicOperation, StrictComparingOperation {
    override val key: String = "!=="

    override fun invoke(expression: Any?, data: Any?): Boolean = with(expression.asList) {
        compareListOfTwoOrDefault(values = this, default = true) { first, second -> first != second }
    }
}

