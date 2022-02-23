package operations.logic

import operations.LogicOperation
import operations.logic.unwrap.TruthyUnwrapStrategy
import utils.asList
import utils.secondOrNull
import utils.thirdOrNull

internal object If : LogicOperation, TruthyUnwrapStrategy {
    override val key: String = "if"

    override fun invoke(expression: Any?, data: Any?): Any? = expression.asList.recursiveIf()

    private fun List<Any?>.recursiveIf(): Any? = when (size) {
        0 -> null
        1 -> firstOrNull()
        2 -> if (unwrapValueAsBoolean(firstOrNull())) secondOrNull() else null
        3 -> if (unwrapValueAsBoolean(firstOrNull())) secondOrNull() else thirdOrNull()
        else -> if (unwrapValueAsBoolean(firstOrNull())) secondOrNull() else subList(2, size).recursiveIf()
    }
}
