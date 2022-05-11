package operations.numeric.compare

import operation.StandardLogicOperation
import utils.asList

internal object LessThanOrEqualTo : StandardLogicOperation, RangeComparingOperation {
    override fun evaluateLogic(expression: Any?, data: Any?): Any =
        compareOrBetween(expression.asList) { first, second -> first <= second }
}
