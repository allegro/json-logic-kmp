package operations.numeric.compare

import operations.LogicOperation
import utils.asList

internal object LessThanOrEqualTo : LogicOperation, RangeComparingOperation {
    override val key: String = "<="

    override fun invoke(expression: Any?, data: Any?): Any =
        compareOrBetween(expression.asList) { first, second -> first <= second }
}
