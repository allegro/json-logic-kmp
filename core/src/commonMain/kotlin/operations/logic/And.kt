package operations.logic

import operation.StandardLogicOperation
import operations.logic.unwrap.TruthyUnwrapStrategy
import utils.asList

internal object And : StandardLogicOperation, TruthyUnwrapStrategy {
    override fun evaluateLogic(expression: Any?, data: Any?) = with(expression.asList) {
        if (all { it is Boolean }) {
            all { unwrapValueAsBoolean(it) }
        } else {
            firstOrNull { !unwrapValueAsBoolean(it) } ?: last()
        }
    }
}
