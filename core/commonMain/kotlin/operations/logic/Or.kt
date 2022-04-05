package operations.logic

import operations.StandardLogicOperation
import operations.logic.unwrap.TruthyUnwrapStrategy
import utils.asList

internal object Or : StandardLogicOperation, TruthyUnwrapStrategy {
    override fun invoke(expression: Any?, data: Any?) = with(expression.asList) {
        if (all { it is Boolean }) {
            firstOrNull { unwrapValueAsBoolean(it) } != null
        } else {
            (firstOrNull { unwrapValueAsBoolean(it) } ?: last())
        }
    }
}
