package operations.numeric

import LogicOperation

object Min : LogicOperation, DoubleTypeSensitiveOperation {
    override val key: String = "min"

    override fun invoke(expression: Any?, data: Any?): Any? = resultOrNull(expression) { it.minOrNull() }
}
