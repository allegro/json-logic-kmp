package operations.array

import operations.LogicOperation
import operations.logic.unwrap.TruthyUnwrapStrategy
import kotlin.collections.Map as MapCollection

internal object Filter : LogicOperation, NoInitialValueOperation, TruthyUnwrapStrategy {
    override val key: String = "filter"

    override fun invoke(expression: Any?, data: Any?): Any? =
        invokeArrayOperation(expression, data, ::filterOrEmptyList)

    private fun filterOrEmptyList(
        operationData: List<Any?>,
        mappingOperation: MapCollection<String, Any>?,
        operationDefault: Any?
    ) = operationData.filter { evaluatedValue ->
        unwrapValueAsBoolean((mappingOperation?.let { operation ->
            evaluate(operation, evaluatedValue)
        } ?: operationDefault))
    }
}
