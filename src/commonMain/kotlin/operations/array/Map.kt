package operations.array

import FunctionalLogicOperation
import LogicOperations
import operations.LogicOperation
import kotlin.collections.Map

internal class Map(operations: LogicOperations) : FunctionalLogicOperation(operations), NoInitialValueOperation {
    override val key: String = "map"

    override fun invoke(expression: Any?, data: Any?): Any? = invokeArrayOperation(expression, data, operations, ::mapOrEmptyList)

    private fun mapOrEmptyList(
        operationData: List<Any?>,
        mappingOperation: Map<String, Any>?,
        operationDefault: Any?
    ) = operationData.map { evaluatedValue ->
        mappingOperation?.let { operation -> evaluateLogic(operation, evaluatedValue, operations) } ?: operationDefault
    }
}
