package operations

import utils.asList
import utils.secondOrNull

internal object In : LogicOperation {
    override val key: String = "in"

    override fun invoke(expression: Any?, data: Any?): Boolean {
        val first = expression.asList.firstOrNull()
        return when (val second = expression.asList.secondOrNull()) {
            is String -> second.contains(first.toString())
            is List<*> -> second.contains(first)
            else -> false
        }
    }
}
