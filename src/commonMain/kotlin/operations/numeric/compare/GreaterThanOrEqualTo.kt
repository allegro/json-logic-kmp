package operations.numeric.compare

import operations.ComparingOperation
import operations.LogicOperation
import utils.asList

internal object GreaterThanOrEqualTo : LogicOperation, ComparingOperation {
    override val key: String = ">="

    override fun invoke(expression: Any?, data: Any?): Any =
        compareListOfTwo(expression.asList) { first, second -> first >= second }
}
