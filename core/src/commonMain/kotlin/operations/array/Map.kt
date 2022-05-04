package operations.array

import LogicEvaluator
import operation.FunctionalLogicOperation
import kotlin.collections.Map

internal object Map : FunctionalLogicOperation, NoInitialValueOperation {
    override fun evaluateLogic(expression: Any?, data: Any?, evaluator: LogicEvaluator): Any? =
        invokeArrayOperation(expression, data, evaluator, ::mapOrEmptyList)

    private fun mapOrEmptyList(
        operationInput: ArrayOperationInputData,
        evaluator: LogicEvaluator
    ) = with(operationInput) {
        operationData.orEmpty().map { evaluatedValue ->
            evaluator.mapValue(evaluatedValue, mappingOperation, operationDefault)
        }
    }

    private fun LogicEvaluator.mapValue(
        evaluatedValue: Any?,
        mappingOperation: Map<String, Any>?,
        operationDefault: Any?
    ) = mappingOperation?.let { operation ->
        evaluateLogic(operation, evaluatedValue)
    } ?: operationDefault
}

