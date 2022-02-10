package operations.numeric

import LogicOperation

object Addition : LogicOperation, DoubleTypeSensitiveOperation {
    override val key: String = "+"

    override fun invoke(expression: Any?, data: Any?): Any? = resultOrNull(expression) { it.sum() }
}
