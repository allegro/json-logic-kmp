package operations.data

import StandardLogicOperation

internal object Missing : StandardLogicOperation {
    override fun invoke(expression: Any?, data: Any?): List<Any?> {
        return (expression as? List<Any?>)?.mapNotNull {
            it.takeIf { Var(it, data).isNullOrEmptyString() }
        }.orEmpty()
    }

    private fun Any?.isNullOrEmptyString() = this == null || (this is String && this.isEmpty())
}
