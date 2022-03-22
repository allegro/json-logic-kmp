package operations.string

import operations.StandardLogicOperation

object Cat : StandardLogicOperation, StringUnwrapStrategy {
    override fun invoke(expression: Any?, data: Any?) = unwrapValueAsString(expression).joinToString("")
}
