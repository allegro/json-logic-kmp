package operations.numeric.compare

import operations.ComparingOperation
import operations.StandardLogicOperation
import utils.asList

internal object GreaterThanOrEqualTo : StandardLogicOperation, ComparingOperation {

    override fun invoke(expression: Any?, data: Any?): Any =
        compareListOfTwo(expression.asList) { first, second -> first >= second }
}
