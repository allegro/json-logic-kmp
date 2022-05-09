package operations.string

import operation.StandardLogicOperation

object Cat : StandardLogicOperation, StringUnwrapStrategy {
    override fun evaluateLogic(expression: Any?, data: Any?): Any? = unwrapValueAsString(expression).joinToString("")
}
