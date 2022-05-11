package operations.numeric

import operation.StandardLogicOperation

internal object Max : StandardLogicOperation, DoubleTypeSensitiveOperation {
    override fun evaluateLogic(expression: Any?, data: Any?): Any? = doubleResultOrNull(expression) { it.maxOrNull() }
}
