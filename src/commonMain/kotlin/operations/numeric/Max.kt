package operations.numeric

import operations.LogicOperation

internal object Max : LogicOperation, DoubleTypeSensitiveOperation {
    override val key: String = "max"

    override fun invoke(expression: Any?, data: Any?): Any? = resultOrNull(expression) { it.maxOrNull() }
}
