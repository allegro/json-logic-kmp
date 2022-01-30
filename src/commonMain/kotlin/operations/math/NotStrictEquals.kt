package operations.math

import operations.LogicExpression
import utils.asComparableList

internal object NotStrictEquals : LogicExpression, ComparingExpression {
    override val key: String = "!=="

    override fun invoke(expression: Any?, data: Any?): Boolean = with(expression.asComparableList) {
        compareStrict(getOrNull(0), getOrNull(1)) != 0
    }
}

