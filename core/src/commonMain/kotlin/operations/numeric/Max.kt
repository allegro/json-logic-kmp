package operations.numeric

import StandardLogicOperation

internal object Max : StandardLogicOperation, DoubleTypeSensitiveOperation {
    override fun invoke(expression: Any?, data: Any?): Any? = doubleResultOrNull(expression) { it.maxOrNull() }
}
