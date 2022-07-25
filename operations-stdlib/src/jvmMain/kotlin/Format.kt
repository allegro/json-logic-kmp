import operation.StandardLogicOperation
import java.lang.String.format

actual object Format : StandardLogicOperation, DecimalFormatter {
    @Suppress("SpreadOperator")
    actual override fun evaluateLogic(expression: Any?, data: Any?): Any? {
        return formatDecimal(expression, data) { sign: String, format: String, arg: String ->
            val formattedArgument = if (sign.contains("f")) {
                arg.toDouble()
            } else {
                arg
            }
            format(format, formattedArgument)
        }
    }
}

