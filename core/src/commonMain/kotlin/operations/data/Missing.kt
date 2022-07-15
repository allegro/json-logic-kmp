package operations.data

import operation.StandardLogicOperation
import utils.asList

internal object Missing : StandardLogicOperation {
    override fun evaluateLogic(expression: Any?, data: Any?): List<Any?> {
        return expression.asList.mapNotNull {
            it.takeIf { Var.evaluateLogic(it, data).isNullOrEmptyString() }
        }
    }

    private fun Any?.isNullOrEmptyString() = this == null || (this is String && this.isEmpty())
}
