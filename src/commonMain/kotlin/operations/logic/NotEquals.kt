package operations.logic

import operations.LogicOperation
import utils.asList
import operations.ComparingOperation

internal object NotEquals : LogicOperation, NoArgumentSafeComparingOperation {
    override val key: String = "!="

    override fun invoke(expression: Any?, data: Any?): Boolean = !sizeSafeCompare(expression) { first, second -> first == second }
}
