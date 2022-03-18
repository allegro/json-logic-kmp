package operations.array

import LogicEvaluator
import operations.FunctionalLogicOperation
import kotlin.collections.Map

internal object Map : FunctionalLogicOperation, NoInitialValueOperation {
    override fun invoke(expression: Any?, data: Any?, evaluator: LogicEvaluator): Any? =
        invokeArrayOperation(expression, data, evaluator, ::mapOrEmptyList)

    private fun mapOrEmptyList(
        operationData: List<Any?>,
        mappingOperation: Map<String, Any>?,
        operationDefault: Any?,
        evaluator: LogicEvaluator
    ) = operationData.map { evaluatedValue ->
        mappingOperation?.let { operation -> evaluator.evaluateLogic(operation, evaluatedValue) } ?: operationDefault
    }
}
