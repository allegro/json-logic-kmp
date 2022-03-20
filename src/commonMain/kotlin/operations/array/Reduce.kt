package operations.array

import LogicEvaluator
import operations.FunctionalLogicOperation
import utils.asList
import utils.thirdOrNull
import kotlin.collections.Map

internal object Reduce : FunctionalLogicOperation, ArrayOperation {
    private const val CURRENT_DATA_KEY = "current"
    private const val ACCUMULATOR_DATA_KEY = "accumulator"

    override fun invoke(expression: Any?, data: Any?, evaluator: LogicEvaluator): Any? =
        expression.asList.let { expressionValues ->
            val evaluatedOperationData = unwrapOperationData(expressionValues, data, evaluator)
            val mappingOperation = getMappingOperationOrNull(expressionValues)
            val operationDefault = getOperationDefault(mappingOperation, expressionValues)
            val initialValue = expressionValues.thirdOrNull()

            reduceOrInitial(evaluatedOperationData, mappingOperation, operationDefault, initialValue, evaluator)
        }

    // TODO too many params
    private fun reduceOrInitial(
        operationData: List<Any?>?,
        mappingOperation: Map<String, Any>?,
        operationDefault: Any?,
        initialValue: Any?,
        evaluator: LogicEvaluator
    ) = operationData?.fold(initialValue) { accumulator, evaluatedValue ->
        reduceValue(mappingOperation, accumulator, evaluatedValue, evaluator) ?: return operationDefault
    } ?: initialValue

    // TODO too many params
    private fun reduceValue(
        mappingOperation: Map<String, Any>?,
        accumulator: Any?,
        evaluatedValue: Any?,
        evaluator: LogicEvaluator
    ) = mappingOperation?.let { operation ->
            evaluator.evaluateLogic(operation, toReduceIterationData(accumulator, evaluatedValue))
        }

    private fun toReduceIterationData(accumulator: Any?, current: Any?) =
        mapOf(ACCUMULATOR_DATA_KEY to accumulator, CURRENT_DATA_KEY to current)
}
