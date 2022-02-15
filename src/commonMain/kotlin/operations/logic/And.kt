package operations.logic

import operations.LogicOperation
import utils.asList
import utils.truthy

internal object And : LogicOperation {
    override val key: String = "and"

    override fun invoke(expression: Any?, data: Any?) = with(expression.asList) {
        if (all { it is Boolean }) {
            all { it.truthy }
        } else {
            (firstOrNull { !it.truthy } ?: last())
        }
    }
}
