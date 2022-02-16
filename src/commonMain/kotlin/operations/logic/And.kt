package operations.logic

import operations.LogicOperation
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
