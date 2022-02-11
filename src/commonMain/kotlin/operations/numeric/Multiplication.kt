package operations.numeric

import JsonLogicException
import LogicOperation
import asList

object Multiplication : LogicOperation, DoubleTypeSensitiveOperation {
    override val key: String = "*"

    override fun invoke(expression: Any?, data: Any?): Any? {
        val values = expression.asList
        return when (values.size) {
            0 -> throw JsonLogicException("")
            1 -> values.first()
            else -> resultOrNull(expression.unwrapValues()) {
                it.reduce { sum: Double, value: Double ->
                    sum * value
                }
            }
        }
    }

    private fun Any?.unwrapValues() = asList.map(::unwrapValueFromList)

    private fun unwrapValueFromList(value: Any?): Any? =
        when (value) {
            is Number -> value.toDouble()
            is String -> value.toDoubleOrNull()
            is List<*> -> unwrapValueFromList(value.firstOrNull())
            else -> null
        }
}
