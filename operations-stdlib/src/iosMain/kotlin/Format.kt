import operation.StandardLogicOperation
import platform.Foundation.NSString
import platform.Foundation.stringWithFormat
import utils.asList
import utils.secondOrNull
import utils.thirdOrNull

actual object Format : StandardLogicOperation {
    actual override fun evaluateLogic(expression: Any?, data: Any?): Any {
        return with(expression.asList) {
            val format = secondOrNull().toString()
            val args = thirdOrNull().asList
            return { NSString.stringWithFormat(format, args) }
        }
    }
}
