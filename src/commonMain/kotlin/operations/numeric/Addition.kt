package operations.numeric

import LogicOperation
import asList

object Addition : LogicOperation, DoubleTypeSensitiveOperation {
    override val key: String = "+"

    override fun invoke(expression: Any?, data: Any?): Any? = resultOrNull(expression.unwrapValues()) { it.sum() }

    private fun Any?.unwrapValues() = asList.map(::unwrapValueFromList)

    private fun unwrapValueFromList(value: Any?): Any? =
        when (value) {
            is Number -> value.toDouble()
            is String -> value.toDoubleOrNull()
            is List<*> -> unwrapValueFromList(value.firstOrNull())
            else -> null
        }
}
