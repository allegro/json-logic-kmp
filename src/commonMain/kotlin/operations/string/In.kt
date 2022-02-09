package operations.string

import LogicOperation
import asDoubleList
import asList

object In : LogicOperation {
    override val key: String = "in"

    override fun invoke(expression: Any?, data: Any?): Boolean {
        val first = expression.asList.firstOrNull().toString()
        val second = expression.asList.getOrNull(1)
        return when (second) {
            is String -> second.contains(first)
            is List<*> -> second.contains(first)
            else -> false
        }
    }
}
