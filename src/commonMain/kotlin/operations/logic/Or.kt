package operations.logic

import operations.LogicOperation
import utils.asList

internal object Or : LogicOperation, TruthyUnwrapStrategy {
    override val key: String = "or"

    override fun invoke(expression: Any?, data: Any?) = with(expression.asList) {
        if (all { it is Boolean }) {
            firstOrNull { unwrapValue(it) } != null
        } else {
            (firstOrNull { unwrapValue(it) } ?: last())
        }
    }
}
