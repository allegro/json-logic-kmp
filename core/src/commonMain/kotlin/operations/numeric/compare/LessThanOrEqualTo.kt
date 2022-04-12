package operations.numeric.compare

import StandardLogicOperation
import utils.asList

internal object LessThanOrEqualTo : StandardLogicOperation, RangeComparingOperation {
    override fun invoke(expression: Any?, data: Any?): Any =
        compareOrBetween(expression.asList) { first, second -> first <= second }
}