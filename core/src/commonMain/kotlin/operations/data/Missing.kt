package operations.data

import operation.StandardLogicOperation

internal object Missing : StandardLogicOperation {
    override fun evaluateLogic(expression: Any?, data: Any?): List<Any?> {
        return (expression as? List<Any?>)?.mapNotNull {
            it.takeIf { Var.evaluateLogic(it, data).isNullOrEmptyString() }
        }.orEmpty()
    }

    private fun Any?.isNullOrEmptyString() = this == null || (this is String && this.isEmpty())
}
