package operations.logic

import operations.LogicOperation
import utils.asList

internal object Negation : LogicOperation, TruthyUnwrapStrategy {
    override val key: String = "!"

    override fun invoke(expression: Any?, data: Any?): Boolean = !unwrapValue(expression.asList.firstOrNull())
}
