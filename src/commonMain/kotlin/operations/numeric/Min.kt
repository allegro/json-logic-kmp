package operations.numeric

import operations.StandardLogicOperation

internal object Min : StandardLogicOperation, DoubleTypeSensitiveOperation {
    override fun invoke(expression: Any?, data: Any?): Any? = doubleResultOrNull(expression) { it.minOrNull() }
}
