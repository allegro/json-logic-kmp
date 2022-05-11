package operations.numeric

import operation.StandardLogicOperation
import operations.numeric.unwrap.LenientUnwrapStrategy

internal object Division : StandardLogicOperation, LenientUnwrapStrategy {
    override fun evaluateLogic(expression: Any?, data: Any?) =
        unwrapValueAsDouble(expression).takeIf { it.size >= 2 }?.let {
            val second = it[1]
            val first = it.first()
            if (first != null && second != null && second != 0.0) {
                first / second
            } else null
        }
}
