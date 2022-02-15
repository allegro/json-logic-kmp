package operations.logic

import utils.asList
import utils.truthy

internal object Negation : operations.LogicOperation {
    override val key: String = "!"

    override fun invoke(expression: Any?, data: Any?): Boolean = !expression.asList.firstOrNull().truthy
}
