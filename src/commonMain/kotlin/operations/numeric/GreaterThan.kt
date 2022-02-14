package operations.numeric

import LogicOperation
import asList

internal object GreaterThan : LogicOperation, ComparingOperation {
    override val key: String = ">"

    override fun invoke(expression: Any?, data: Any?): Any =
        expression.asList.compareListOfTwo { first, second -> first > second }
}
