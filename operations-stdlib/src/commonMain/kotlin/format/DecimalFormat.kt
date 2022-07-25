package format

import operation.StandardLogicOperation

expect object DecimalFormat : StandardLogicOperation, DecimalFormatter {
    override fun evaluateLogic(expression: Any?, data: Any?): Any?
}

