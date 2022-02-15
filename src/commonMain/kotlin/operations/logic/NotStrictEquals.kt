package operations.logic

import LogicOperation
import asList
import comparableList
import operations.numeric.compare.ComparingOperation
import operations.numeric.compare.StrictComparingOperation
import secondOrNull

internal object NotStrictEquals : LogicOperation, StrictComparingOperation {
    override val key: String = "!=="

    override fun invoke(expression: Any?, data: Any?): Boolean = with(expression.asList) {
        compareListOfTwoOrDefault(values = this, default = true) { first, second -> first != second }
    }
}

