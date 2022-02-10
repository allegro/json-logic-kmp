package operations.numeric

import LogicOperation

object Max : LogicOperation, DoubleTypeSensitiveOperation {
    override val key: String = "max"

    override fun invoke(expression: Any?, data: Any?): Any? = resultOrNull(expression) { it.maxOrNull() }
}
