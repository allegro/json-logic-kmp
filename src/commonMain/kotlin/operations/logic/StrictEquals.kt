package operations.logic

import operations.LogicExpression
import operations.logic.Equals.compareStrict
import utils.asComparableList

internal object StrictEquals : LogicExpression {
    override val key: String = "==="

    override fun invoke(expression: Any?, data: Any?): Boolean = with(expression.asComparableList) {
        compareStrict(getOrNull(0), getOrNull(1)) == 0
    }
}


