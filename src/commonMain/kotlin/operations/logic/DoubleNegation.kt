package operations.logic

import operations.LogicOperation
import operations.logic.unwrap.TruthyUnwrapStrategy
import utils.asList

internal object DoubleNegation : LogicOperation, TruthyUnwrapStrategy {
    override val key: String = "!!"

    override fun invoke(expression: Any?, data: Any?): Boolean = unwrapValueAsBoolean(expression.asList.firstOrNull())
}
