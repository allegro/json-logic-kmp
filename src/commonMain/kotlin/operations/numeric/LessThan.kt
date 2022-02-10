package operations.numeric

import LogicOperation
import asList

object LessThan : LogicOperation, ComparingOperation {
    override val key: String = "<"

    override fun invoke(expression: Any?, data: Any?): Any =
        expression.asList.compareOrBetween { first, second -> first < second }
}
