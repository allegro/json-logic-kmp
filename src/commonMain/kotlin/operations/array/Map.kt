package operations.array

import operations.LogicOperation
import kotlin.collections.Map

internal object Map : LogicOperation, NoInitialValueOperation {
    override val key: String = "map"

    override fun invoke(expression: Any?, data: Any?): Any? = invokeArrayOperation(expression, data, ::mapOrEmptyList)

    private fun mapOrEmptyList(
        operationData: List<Any?>,
        mappingOperation: Map<String, Any>?,
        operationDefault: Any?
    ) = operationData.map { evaluatedValue ->
        mappingOperation?.let { operation -> evaluateLogic(operation, evaluatedValue) } ?: operationDefault
    }
}
