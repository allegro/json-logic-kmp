package operations.string

import LogicOperation
import asList
import toStringOrEmpty

object Cat : LogicOperation {
    override val key: String = "cat"

    override fun invoke(expression: Any?, data: Any?) = expression.asList
        .map(::formatValues)
        .joinToString("")

    private fun formatValues(value: Any?): Any? {
        return when {
            (value is Number && value.toDouble() == value.toInt().toDouble()) -> value.toInt()
            value is List<*> -> value.map(::formatValues).joinToString(separator = ",")
            else -> value.toStringOrEmpty()
        }
    }
}
