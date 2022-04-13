package operations

import StandardLogicOperation
import utils.asList
import utils.secondOrNull

internal object In : StandardLogicOperation {
    override fun invoke(expression: Any?, data: Any?): Boolean {
        val first = expression.asList.firstOrNull()
        return when (val second = expression.asList.secondOrNull()) {
            is String -> second.contains(first.toString())
            is List<*> -> second.contains(first)
            else -> false
        }
    }
}
