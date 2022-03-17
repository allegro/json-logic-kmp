package operations

import utils.asList

internal class Log(private val logger: ((Any?) -> Unit)? = null) : LogicOperation {
    override val key: String = "log"

    override fun invoke(expression: Any?, data: Any?): Any? {
        val loggedValue = expression.asList.firstOrNull()
        logger?.let { log -> log(loggedValue) }
        return loggedValue
    }
}
