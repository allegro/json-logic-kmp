package string

import operation.StandardLogicOperation

object Lowercase : StandardLogicOperation, StringUnwrapStrategy {
    override fun evaluateLogic(expression: Any?, data: Any?): Any? =
        unwrapValueAsString(expression)?.replaceFirstChar { it.lowercase() }
}
