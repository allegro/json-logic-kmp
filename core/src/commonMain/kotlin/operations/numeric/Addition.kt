package operations.numeric

import operation.StandardLogicOperation
import operations.numeric.unwrap.StrictUnwrapStrategy

internal object Addition : StandardLogicOperation, DoubleTypeSensitiveOperation, StrictUnwrapStrategy {
    override fun evaluateLogic(
        expression: Any?,
        data: Any?
    ): Any? = doubleResultOrNull(unwrapValue(expression)) { it.sum() }
}
