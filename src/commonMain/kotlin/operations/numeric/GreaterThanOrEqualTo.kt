package operations.numeric

import LogicOperation
import asList

internal object GreaterThanOrEqualTo : LogicOperation, ComparingOperation {
    override val key: String = ">="

    override fun invoke(expression: Any?, data: Any?): Any =
        compareListOfTwo(expression.asList) { first, second -> first >= second }
}
