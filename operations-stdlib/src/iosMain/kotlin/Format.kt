import kotlinx.cinterop.cstr
import operation.StandardLogicOperation
import platform.Foundation.NSString
import platform.Foundation.stringWithFormat

actual object Format : StandardLogicOperation, DecimalFormatter {
    actual override fun evaluateLogic(expression: Any?, data: Any?): Any? {
        return formatDecimal(expression, data) { sign: String, format: String, arg: String ->
            if (sign.contains("f")) {
                NSString.stringWithFormat(format, arg.toDouble())
            } else {
                NSString.stringWithFormat(format, arg.cstr)
            }
        }
    }
}
