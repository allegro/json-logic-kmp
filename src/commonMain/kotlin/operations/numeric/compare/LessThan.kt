package operations.numeric.compare

import operations.StandardLogicOperation
import utils.asList

internal object LessThan : StandardLogicOperation, RangeComparingOperation {
    override fun invoke(expression: Any?, data: Any?): Any =
        compareOrBetween(expression.asList) { first, second -> first < second }
}
