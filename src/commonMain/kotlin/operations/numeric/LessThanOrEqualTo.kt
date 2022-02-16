package operations.numeric

import LogicOperation
import asList

internal object LessThanOrEqualTo : LogicOperation, ComparingOperation {
    override val key: String = "<="

    override fun invoke(expression: Any?, data: Any?): Any =
        compareOrBetween(expression.asList) { first, second -> first <= second }
}
