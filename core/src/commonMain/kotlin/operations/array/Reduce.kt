package operations.array

import LogicEvaluator
import operation.FunctionalLogicOperation
import utils.asList
import utils.thirdOrNull
import kotlin.collections.Map

internal object Reduce : FunctionalLogicOperation, ArrayOperation {
    private const val CURRENT_DATA_KEY = "current"
    private const val ACCUMULATOR_DATA_KEY = "accumulator"

    override fun evaluateLogic(expression: Any?, data: Any?, evaluator: LogicEvaluator): Any? =
        expression.asList.let { expressionValues ->
            val input = createOperationInput(expressionValues, data, evaluator)
            val initialValue = expressionValues.thirdOrNull()

            reduceOrInitial(input, initialValue, evaluator)
        }

    private fun reduceOrInitial(
        operationInput: ArrayOperationInputData,
        initialValue: Any?,
        evaluator: LogicEvaluator
    ) = with(operationInput) {
        operationData?.fold(initialValue) { accumulator, evaluatedValue ->
            evaluator.reduceValue(accumulator, evaluatedValue, mappingOperation) ?: return operationDefault
        } ?: initialValue
    }

    private fun LogicEvaluator.reduceValue(
        accumulator: Any?,
        evaluatedValue: Any?,
        mappingOperation: Map<String, Any>?,
    ) = mappingOperation?.let { operation ->
        evaluateLogic(operation, toReduceIterationData(accumulator, evaluatedValue))
    }

    private fun toReduceIterationData(accumulator: Any?, current: Any?) =
        mapOf(ACCUMULATOR_DATA_KEY to accumulator, CURRENT_DATA_KEY to current)
}
