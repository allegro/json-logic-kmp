package operations.logic

import operations.LogicOperation
import utils.asList
import utils.truthy

internal object Or : LogicOperation {
    override val key: String = "or"

    override fun invoke(expression: Any?, data: Any?) = with(expression.asList) {
        if (all { it is Boolean }) {
            firstOrNull { it.truthy } != null
        } else {
            (firstOrNull { it.truthy } ?: last())
        }
    }
}
