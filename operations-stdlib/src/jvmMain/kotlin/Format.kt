import operation.StandardLogicOperation
import utils.asList
import utils.secondOrNull

actual object Format : StandardLogicOperation {
    actual override fun evaluateLogic(expression: Any?, data: Any?): Any? {
        return with(expression.asList) {
            val format = firstOrNull().toString()
            val args = secondOrNull().asList

            return kotlin.runCatching { String.format(format, args) }.fold( { it }, { null } )
        }
    }
}
