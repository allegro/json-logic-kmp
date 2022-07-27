package format

import operation.StandardLogicOperation
import java.lang.String.format

actual object DecimalFormat : StandardLogicOperation, DecimalFormatter {
    actual override fun evaluateLogic(expression: Any?, data: Any?): Any? {
        return formatDecimal(expression, data) { formatSequence: String, arg: Double ->
            format(formatSequence, arg)
        }
    }
}
