package operations.logic

import operations.LogicOperation
import operations.logic.unwrap.TruthyUnwrapStrategy
import utils.asList

internal object And : LogicOperation, TruthyUnwrapStrategy {
    override val key: String = "and"

    override fun invoke(expression: Any?, data: Any?) = with(expression.asList) {
        if (all { it is Boolean }) {
            all { unwrapValue(it) }
        } else {
            (firstOrNull { !unwrapValue(it) } ?: last())
        }
    }
}
