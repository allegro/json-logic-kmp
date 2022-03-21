package operations.array

import LogicEvaluator
import operations.FunctionalLogicOperation
import operations.logic.unwrap.TruthyUnwrapStrategy
import kotlin.collections.Map

internal object Filter : FunctionalLogicOperation, NoInitialValueOperation, TruthyUnwrapStrategy {
    override fun invoke(expression: Any?, data: Any?, evaluator: LogicEvaluator): Any? =
        invokeArrayOperation(expression, data, evaluator, ::filterOrEmptyList)

    private fun filterOrEmptyList(
        operationInput: ArrayOperationInputData,
        evaluator: LogicEvaluator
    ) = with(operationInput) {
        operationData.orEmpty().filter { evaluatedValue ->
            evaluator.filterValue(evaluatedValue, mappingOperation, operationDefault)
        }
    }

    private fun LogicEvaluator.filterValue(
        evaluatedValue: Any?,
        mappingOperation: Map<String, Any>?,
        operationDefault: Any?
    ) = unwrapValueAsBoolean((mappingOperation?.let { operation ->
        evaluateLogic(operation, evaluatedValue)
    } ?: operationDefault))
}
