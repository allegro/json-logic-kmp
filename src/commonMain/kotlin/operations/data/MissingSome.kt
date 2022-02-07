package operations.data

import LogicOperation
import intOrZero

internal object MissingSome : LogicOperation {
    override val key: String = "missing_some"

    override fun invoke(expression: Any?, data: Any?): Any {
        val min = (expression as? List<Any?>?)?.firstOrNull()?.toString()?.intOrZero ?: 0
        val keys = ((expression as? List<Any?>?)?.getOrNull(1) as? List<Any?>).orEmpty()
        val missing = Missing(keys, data)
        return missing.takeIf { keys.size - missing.size < min }.orEmpty()
    }
}
