package string

import operation.StandardLogicOperation

object Uppercase : StandardLogicOperation, StringUnwrapStrategy {
    override fun evaluateLogic(expression: Any?, data: Any?): Any? =
        unwrapValueAsString(expression)?.uppercase()
}
