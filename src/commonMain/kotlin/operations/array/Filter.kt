package operations.array

import LogicEvaluator
import operations.FunctionalLogicOperation
import operations.logic.unwrap.TruthyUnwrapStrategy
import kotlin.collections.Map as MapCollection

internal object Filter : FunctionalLogicOperation, NoInitialValueOperation, TruthyUnwrapStrategy {
    override fun invoke(expression: Any?, data: Any?, evaluator: LogicEvaluator): Any? =
        invokeArrayOperation(expression, data, evaluator, ::filterOrEmptyList)

    private fun filterOrEmptyList(
        operationData: List<Any?>,
        mappingOperation: MapCollection<String, Any>?,
        operationDefault: Any?,
        evaluator: LogicEvaluator
    ) = operationData.filter { evaluatedValue ->
        unwrapValueAsBoolean((mappingOperation?.let { operation ->
            evaluator.evaluateLogic(operation, evaluatedValue)
        } ?: operationDefault))
    }
}
