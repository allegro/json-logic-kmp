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
            else -> resultOrNull(expression) {
                it.reduce { sum: Double, value: Double ->
                    sum * value
                }
            }
        }
    }
}
