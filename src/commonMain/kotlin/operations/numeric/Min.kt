package operations.numeric

import LogicOperation

internal object Min : LogicOperation, DoubleTypeSensitiveOperation {
    override val key: String = "min"

    override fun invoke(expression: Any?, data: Any?): Any? = doubleResultOrNull(expression) { it.minOrNull() }
}
