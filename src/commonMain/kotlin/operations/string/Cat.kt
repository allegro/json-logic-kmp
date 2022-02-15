package operations.string

import LogicOperation

object Cat : LogicOperation, StringUnwrapStrategy {
    override val key: String = "cat"

    override fun invoke(expression: Any?, data: Any?) = unwrapValues(expression).joinToString("")
}
