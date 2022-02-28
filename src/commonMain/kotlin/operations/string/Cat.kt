package operations.string

import operations.LogicOperation

object Cat : LogicOperation, StringUnwrapStrategy {
    override val key: String = "cat"

    override fun invoke(expression: Any?, data: Any?) = unwrapValueAsString(expression).joinToString("")
}
