package operations.data

import operations.StandardLogicOperation
import utils.longOrZero
import utils.secondOrNull

internal object MissingSome : StandardLogicOperation {
    override fun invoke(expression: Any?, data: Any?): Any {
        val min = (expression as? List<Any?>?)?.firstOrNull()?.toString()?.longOrZero ?: 0
        val keys = ((expression as? List<Any?>?)?.secondOrNull() as? List<Any?>).orEmpty()
        val missing = Missing(keys, data)
        return missing.takeIf { keys.size - missing.size < min }.orEmpty()
    }
}
