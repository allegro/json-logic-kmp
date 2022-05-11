package operations.data

import operation.StandardLogicOperation
import utils.longOrZero
import utils.secondOrNull

internal object MissingSome : StandardLogicOperation {
    override fun evaluateLogic(expression: Any?, data: Any?): Any {
        val min = (expression as? List<Any?>?)?.firstOrNull()?.toString()?.longOrZero ?: 0
        val keys = ((expression as? List<Any?>?)?.secondOrNull() as? List<Any?>).orEmpty()
        val missing = Missing.evaluateLogic(keys, data)
        return missing.takeIf { keys.size - missing.size < min }.orEmpty()
    }
}
