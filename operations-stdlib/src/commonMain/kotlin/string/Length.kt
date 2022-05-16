package string

import operation.StandardLogicOperation

object Length : StandardLogicOperation, StringUnwrapStrategy {
    override fun evaluateLogic(expression: Any?, data: Any?): Any? = unwrapValueAsString(expression)?.length
}
