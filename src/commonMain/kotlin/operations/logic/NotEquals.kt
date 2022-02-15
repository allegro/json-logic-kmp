package operations.logic

import operations.LogicOperation
import utils.asList
import operations.numeric.compare.ComparingOperation

internal object NotEquals : LogicOperation, ComparingOperation {
    override val key: String = "!="

    override fun invoke(expression: Any?, data: Any?): Boolean = with(expression.asList) {
        compareListOfTwo(this) { first, second -> first != second }
    }
}

