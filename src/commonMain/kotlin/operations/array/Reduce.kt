package operations.array

import operations.LogicOperation
import utils.asList
import utils.secondOrNull
import utils.thirdOrNull
import kotlin.collections.Map as MapCollection

internal object Reduce : LogicOperation, ArrayOperation {
    override val key: String = "reduce"
    private const val CURRENT_DATA_KEY = "current"
    private const val ACCUMULATOR_DATA_KEY = "accumulator"

    // TODO add test for size < 2
    override fun invoke(expression: Any?, data: Any?): Any? =
        expression.asList.takeIf { it.size >= 2 }?.let { expressionValues ->
            val evaluatedOperationData = unwrapOperationData(expressionValues, data)
            val mappingOperation = getMappingOperationOrNull(expressionValues)
            val initialValue = expressionValues.thirdOrNull()

            reduceOrInitial(evaluatedOperationData, mappingOperation, expressionValues.secondOrNull(), initialValue)
        }

    private fun reduceOrInitial(
        operationData: List<Any?>?,
        mappingOperation: MapCollection<String, Any>?,
        operationDefault: Any?,
        initialValue: Any?
    ) = operationData?.fold(initialValue) { accumulator, evaluatedValue ->
        reduceValue(mappingOperation, accumulator, evaluatedValue) ?: operationDefault
    } ?: initialValue

    private fun reduceValue(mappingOperation: MapCollection<String, Any>?, accumulator: Any?, evaluatedValue: Any?) =
        mappingOperation?.let { operation ->
            evaluate(operation, toReduceIterationData(accumulator, evaluatedValue))
        }

    private fun toReduceIterationData(accumulator: Any?, current: Any?) =
        mapOf(ACCUMULATOR_DATA_KEY to accumulator, CURRENT_DATA_KEY to current)
}
