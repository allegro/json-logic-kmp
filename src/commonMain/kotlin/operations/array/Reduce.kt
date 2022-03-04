package operations.array

import operations.LogicOperation
import utils.asList
import utils.thirdOrNull
import kotlin.collections.Map

internal object Reduce : LogicOperation, ArrayOperation {
    override val key: String = "reduce"
    private const val CURRENT_DATA_KEY = "current"
    private const val ACCUMULATOR_DATA_KEY = "accumulator"

    override fun invoke(expression: Any?, data: Any?): Any? =
        expression.asList.let { expressionValues ->
            val evaluatedOperationData = unwrapOperationData(expressionValues, data)
            val mappingOperation = getMappingOperationOrNull(expressionValues)
            val operationDefault = getOperationDefault(mappingOperation, expressionValues)
            val initialValue = expressionValues.thirdOrNull()

            reduceOrInitial(evaluatedOperationData, mappingOperation, operationDefault, initialValue)
        }

    private fun reduceOrInitial(
        operationData: List<Any?>?,
        mappingOperation: Map<String, Any>?,
        operationDefault: Any?,
        initialValue: Any?
    ) = operationData?.fold(initialValue) { accumulator, evaluatedValue ->
        reduceValue(mappingOperation, accumulator, evaluatedValue) ?: operationDefault
    } ?: initialValue

    private fun reduceValue(mappingOperation: Map<String, Any>?, accumulator: Any?, evaluatedValue: Any?) =
        mappingOperation?.let { operation ->
            evaluate(operation, toReduceIterationData(accumulator, evaluatedValue))
        }

    private fun toReduceIterationData(accumulator: Any?, current: Any?) =
        mapOf(ACCUMULATOR_DATA_KEY to accumulator, CURRENT_DATA_KEY to current)
}
