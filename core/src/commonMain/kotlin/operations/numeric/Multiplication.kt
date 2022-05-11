package operations.numeric

import operation.StandardLogicOperation
import operations.numeric.unwrap.StrictUnwrapStrategy
import utils.asList

internal object Multiplication : StandardLogicOperation, DoubleTypeSensitiveOperation, StrictUnwrapStrategy {
    override fun evaluateLogic(expression: Any?, data: Any?): Any? {
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
