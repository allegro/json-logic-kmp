package operations.logic

import operations.LogicOperation
import operations.logic.unwrap.TruthyUnwrapStrategy
import utils.asList

internal object Or : LogicOperation, TruthyUnwrapStrategy {
    override val key: String = "or"

    override fun invoke(expression: Any?, data: Any?) = with(expression.asList) {
        if (all { it is Boolean }) {
            firstOrNull { unwrapValueAsBoolean(it) } != null
        } else {
            (firstOrNull { unwrapValueAsBoolean(it) } ?: last())
        }
    }
}
