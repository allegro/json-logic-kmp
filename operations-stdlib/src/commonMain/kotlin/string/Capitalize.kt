package string

import operation.StandardLogicOperation

object Capitalize : StandardLogicOperation, StringUnwrapStrategy {
    override fun evaluateLogic(expression: Any?, data: Any?): Any? =
        unwrapValueAsString(expression)?.replaceFirstChar { it.uppercase() }
}
