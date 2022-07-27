package format

import kotlinx.cinterop.cstr
import operation.StandardLogicOperation
import platform.Foundation.NSString
import platform.Foundation.stringWithFormat

actual object DecimalFormat : StandardLogicOperation, DecimalFormatter {
    actual override fun evaluateLogic(expression: Any?, data: Any?): Any? {
        return formatDecimal(expression, data) { format: String, arg: Double ->
                NSString.stringWithFormat(format, arg)
        }
    }
}
