package operations.numeric

import operations.LogicOperation
import operations.numeric.unwrap.StrictUnwrapStrategy
import utils.asList

internal object Multiplication : LogicOperation, DoubleTypeSensitiveOperation, StrictUnwrapStrategy {
    override val key: String = "*"

    override fun invoke(expression: Any?, data: Any?): Any? {
        val values = expression.asList
        return when (values.size) {
            0 -> null
            1 -> values.first()
            else -> doubleResultOrNull(unwrapValue(expression)) {
                it.reduce { sum: Double, value: Double ->
                    sum * value
                }
            }
        }
    }
}
