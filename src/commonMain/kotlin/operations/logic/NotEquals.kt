package operations.logic

import operations.LogicExpression
import utils.asComparableList

internal object NotEquals : LogicExpression, ComparingExpression {
    override val key: String = "!="

    override fun invoke(expression: Any?, data: Any?): Boolean = with(expression.asComparableList) {
        compare(firstOrNull(), getOrNull(1)) != 0
    }
}

