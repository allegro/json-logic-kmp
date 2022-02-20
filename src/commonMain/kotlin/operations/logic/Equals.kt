package operations.logic

import operations.LogicOperation
import utils.asList

internal object Equals : LogicOperation, NoArgumentSafeComparingOperation {
    override val key: String = "=="

    override fun invoke(expression: Any?, data: Any?): Boolean = sizeSafeCompare(expression) { first, second -> first == second }
}
