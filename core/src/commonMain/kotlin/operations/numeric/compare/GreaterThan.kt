package operations.numeric.compare

import operation.StandardLogicOperation
import operations.ComparingOperation
import utils.asList

internal object GreaterThan : StandardLogicOperation, ComparingOperation {
    override fun evaluateLogic(expression: Any?, data: Any?): Any =
        compareListOfTwo(expression.asList) { first, second -> first > second }
}
