package operations.data

import LogicOperation

internal object Missing : LogicOperation {
    override val key: String = "missing"

    override fun invoke(expression: Any?, data: Any?): List<Any?> {
        return (expression as? List<Any?>)?.mapNotNull {
            it.takeIf { Var(it, data) == null }
        }.orEmpty()
    }
}
