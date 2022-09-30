package encoding

import operation.StandardLogicOperation

expect object Encode: StandardLogicOperation {
    override fun evaluateLogic(expression: Any?, data: Any?): Any?
}
