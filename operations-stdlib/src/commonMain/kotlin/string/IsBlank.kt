package string

import operation.StandardLogicOperation

object IsBlank : StandardLogicOperation, StringUnwrapStrategy {
    override fun evaluateLogic(expression: Any?, data: Any?): Any? = unwrapValueAsString(expression)?.isBlank()
}
