package operations.logic

import LogicOperation
import asList
import comparableList
import operations.numeric.compare.ComparingOperation
import operations.numeric.compare.StrictComparingOperation
import secondOrNull

internal object StrictEquals : LogicOperation, StrictComparingOperation {
    override val key: String = "==="

    override fun invoke(expression: Any?, data: Any?): Boolean = with(expression.asList) {
        compareListOfTwoOrDefault(values = this, default = false) { first, second -> first == second }
    }
}


