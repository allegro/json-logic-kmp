package operations

import operation.StandardLogicOperation
import utils.asList

internal class Log(private val logger: ((Any?) -> Unit)? = null) : StandardLogicOperation {
    override fun evaluateLogic(expression: Any?, data: Any?): Any? {
        val loggedValue = expression.asList.firstOrNull()
        logger?.let { log -> log(loggedValue) }
        return loggedValue
    }
}
