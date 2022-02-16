package operations.numeric

import LogicOperation
import operations.numeric.unwrap.StrictUnwrapStrategy

internal object Addition : LogicOperation, DoubleTypeSensitiveOperation, StrictUnwrapStrategy {
    override val key: String = "+"

    override fun invoke(expression: Any?, data: Any?): Any? = doubleResultOrNull(unwrapValues(expression)) { it.sum() }
}
