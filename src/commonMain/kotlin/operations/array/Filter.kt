package operations.array

import FunctionalLogicOperation
import LogicOperations
import operations.logic.unwrap.TruthyUnwrapStrategy
import kotlin.collections.Map as MapCollection

internal class Filter(operations: LogicOperations) : FunctionalLogicOperation(operations), NoInitialValueOperation,
    TruthyUnwrapStrategy {
    override val key: String = "filter"

    override fun invoke(expression: Any?, data: Any?): Any? =
        invokeArrayOperation(expression, data, operations, ::filterOrEmptyList)

    private fun filterOrEmptyList(
        operationData: List<Any?>,
        mappingOperation: MapCollection<String, Any>?,
        operationDefault: Any?
    ) = operationData.filter { evaluatedValue ->
        unwrapValueAsBoolean((mappingOperation?.let { operation ->
            evaluateLogic(operation, evaluatedValue, operations)
        } ?: operationDefault))
    }
}
