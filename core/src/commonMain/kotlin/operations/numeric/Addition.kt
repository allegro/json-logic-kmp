package operations.numeric

import StandardLogicOperation
import operations.numeric.unwrap.StrictUnwrapStrategy

internal object Addition : StandardLogicOperation, DoubleTypeSensitiveOperation, StrictUnwrapStrategy {

    override fun invoke(expression: Any?, data: Any?): Any? = doubleResultOrNull(unwrapValue(expression)) { it.sum() }
}
