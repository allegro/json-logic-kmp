package operations.numeric

import LogicOperation
import asList
import operations.numeric.unwrap.StrictUnwrapStrategy

object Addition : LogicOperation, DoubleTypeSensitiveOperation, StrictUnwrapStrategy {
    override val key: String = "+"

    override fun invoke(expression: Any?, data: Any?): Any? = resultOrNull(expression.unwrapValues()) { it.sum() }
}
