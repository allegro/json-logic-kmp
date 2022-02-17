package operations.logic

import operations.ComparingOperation
import operations.LogicOperation
import operations.UnwrapStrategy
import utils.asList

internal object Equals : LogicOperation, NoArgumentSafeComparingOperation {
    override val key: String = "=="

    override fun invoke(expression: Any?, data: Any?): Boolean = with(expression.asList) {
        sizeSafeCompare(this) { first, second -> first == second }
    }
}

