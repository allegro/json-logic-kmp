package string

import operation.StandardLogicOperation
import utils.asList

object ToArray : StandardLogicOperation {
    override fun evaluateLogic(expression: Any?, data: Any?): Any? =
        (expression.asList.firstOrNull() as? String)?.split("")?.drop(1)?.dropLast(1)
}
