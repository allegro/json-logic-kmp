package operations.data

import operations.LogicExpression
import utils.intValue

internal object MissingSome : LogicExpression {
    override val key: String = "missing_some"

    override fun invoke(expression: Any?, data: Any?): Any {
        val min = (expression as? List<Any?>?)?.firstOrNull()?.toString()?.intValue ?: 0
        val keys = ((expression as? List<Any?>?)?.getOrNull(1) as? List<Any?>).orEmpty()
        val missing = Missing(keys, data)
        return missing.takeIf { keys.size - missing.size < min }.orEmpty()
    }
}
