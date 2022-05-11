package string

import operation.StandardLogicOperation

object Lowercase : StandardLogicOperation {
    override fun evaluateLogic(expression: Any?, data: Any?): Any? {
        return expression?.toString()?.lowercase()
    }
}
