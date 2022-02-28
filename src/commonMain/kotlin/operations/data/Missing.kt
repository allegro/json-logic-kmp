package operations.data

import operations.LogicOperation

internal object Missing : LogicOperation {
    override val key: String = "missing"

    override fun invoke(expression: Any?, data: Any?): List<Any?> {
        return (expression as? List<Any?>)?.mapNotNull {
            it.takeIf { Var(it, data).isNullOrEmptyString() }
        }.orEmpty()
    }

    private fun Any?.isNullOrEmptyString() = this == null || (this is String && this.isEmpty())
}
