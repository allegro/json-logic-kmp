package operations.numeric

import operations.LogicOperation
import operations.numeric.unwrap.StrictUnwrapStrategy

internal object Addition : LogicOperation, DoubleTypeSensitiveOperation, StrictUnwrapStrategy {
    override val key: String = "+"

    override fun invoke(expression: Any?, data: Any?): Any? = resultOrNull(unwrapValues(expression)) { it.sum() }
}
