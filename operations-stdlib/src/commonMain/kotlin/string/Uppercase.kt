package string

import operation.StandardLogicOperation

object Uppercase : StandardLogicOperation {
    override fun evaluateLogic(expression: Any?, data: Any?): Any? {
        return expression?.toString()?.uppercase()
    }
}
