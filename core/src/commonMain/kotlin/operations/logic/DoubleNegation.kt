package operations.logic

import operation.StandardLogicOperation
import operations.logic.unwrap.TruthyUnwrapStrategy
import utils.asList

internal object DoubleNegation : StandardLogicOperation, TruthyUnwrapStrategy {
    override fun evaluateLogic(
        expression: Any?,
        data: Any?
    ): Boolean = unwrapValueAsBoolean(expression.asList.firstOrNull())
}
