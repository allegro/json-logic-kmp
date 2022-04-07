package operations.logic

import StandardLogicOperation
import operations.logic.unwrap.TruthyUnwrapStrategy
import utils.asList

internal object Negation : StandardLogicOperation, TruthyUnwrapStrategy {
    override fun invoke(expression: Any?, data: Any?): Boolean = !unwrapValueAsBoolean(expression.asList.firstOrNull())
}
