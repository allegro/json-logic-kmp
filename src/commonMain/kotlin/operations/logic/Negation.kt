package operations.logic

import operations.LogicOperation
import operations.logic.unwrap.TruthyUnwrapStrategy
import utils.asList

internal object Negation : LogicOperation, TruthyUnwrapStrategy {
    override val key: String = "!"

    override fun invoke(expression: Any?, data: Any?): Boolean = !unwrapValue(expression.asList.firstOrNull())
}
