package operations.string

import operations.LogicOperation
import utils.asList

object In : LogicOperation {
    override val key: String = "in"

    override fun invoke(expression: Any?, data: Any?): Boolean {
        val first = expression.asList.firstOrNull()
        return when (val second = expression.asList.getOrNull(1)) {
            is String -> second.contains(first.toString())
            is List<*> -> second.contains(first)
            else -> false
        }
    }
}
