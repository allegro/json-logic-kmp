package operations.numeric.compare

import LogicOperation
import asList

internal object LessThanOrEqualTo : LogicOperation, RangeComparingOperation {
    override val key: String = "<="

    override fun invoke(expression: Any?, data: Any?): Any =
        compareOrBetween(expression.asList) { first, second -> first <= second }
}
