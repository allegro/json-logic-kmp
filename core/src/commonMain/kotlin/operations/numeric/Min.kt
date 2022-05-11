package operations.numeric

import operation.StandardLogicOperation

internal object Min : StandardLogicOperation, DoubleTypeSensitiveOperation {
    override fun evaluateLogic(expression: Any?, data: Any?): Any? = doubleResultOrNull(expression) { it.minOrNull() }
}
