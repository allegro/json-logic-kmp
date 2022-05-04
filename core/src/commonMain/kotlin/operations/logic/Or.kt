package operations.logic

import operation.StandardLogicOperation
import operations.logic.unwrap.TruthyUnwrapStrategy
import utils.asList

internal object Or : StandardLogicOperation, TruthyUnwrapStrategy {
    override fun evaluateLogic(expression: Any?, data: Any?) = with(expression.asList) {
        if (all { it is Boolean }) {
            firstOrNull { unwrapValueAsBoolean(it) } != null
        } else {
            (firstOrNull { unwrapValueAsBoolean(it) } ?: last())
        }
    }
}
