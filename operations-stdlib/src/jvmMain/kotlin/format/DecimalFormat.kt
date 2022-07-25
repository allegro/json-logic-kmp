package format

import operation.StandardLogicOperation
import java.lang.String.format

actual object DecimalFormat : StandardLogicOperation, DecimalFormatter {
    actual override fun evaluateLogic(expression: Any?, data: Any?): Any? {
        return formatDecimal(expression, data) { sign: String, formatSequence: String, arg: String ->
            val formattedArgument = if (sign.contains("f")) {
                arg.toDouble()
            } else {
                arg
            }
            format(formatSequence, formattedArgument)
        }
    }
}
