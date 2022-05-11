package string

import operation.StandardLogicOperation

object Capitalize : StandardLogicOperation {
    override fun evaluateLogic(expression: Any?, data: Any?): Any? {
        return expression?.toString()?.replaceFirstChar { it.uppercase() }
    }
}
