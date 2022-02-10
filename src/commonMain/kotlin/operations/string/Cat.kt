package operations.string

import LogicOperation
import asList

object Cat : LogicOperation {
    override val key: String = "cat"

    override fun invoke(expression: Any?, data: Any?) = expression.asList
        .map(::formatNumberValues)
        .joinToString("")

    private fun formatNumberValues(value: Any?): Any? {
        return if (value is Number && value.toDouble() == value.toInt().toDouble()) {
            value.toInt()
        } else value
    }
}
