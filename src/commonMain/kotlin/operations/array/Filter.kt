package operations.array

import operations.LogicOperation
import operations.logic.unwrap.TruthyUnwrapStrategy
import utils.asList
import utils.secondOrNull
import kotlin.collections.Map as MapCollection

internal object Filter : LogicOperation, ArrayOperation, TruthyUnwrapStrategy {
    override val key: String = "filter"

    // TODO add test for size < 2
    override fun invoke(expression: Any?, data: Any?): Any? =
        expression.asList.takeIf { it.size >= 2 }?.let { expressionValues ->
            val evaluatedOperationData = expressionValues.evaluateOperationData(data)
            val mappingOperation = expressionValues.getMappingOperationOrNull()
            evaluatedOperationData.filter { evaluatedValue ->
                // TODO extract
                unwrapValueAsBoolean((mappingOperation?.let { evaluate(it, evaluatedValue) } ?: expressionValues.secondOrNull()))
            }
        }

    private fun List<Any?>.evaluateOperationData(data: Any?) =
        (firstOrNull().unwrapOperationData(data) as? List<*>).orEmpty()

    private fun List<Any?>.getMappingOperationOrNull() =
        secondOrNull().takeIf { it.isNotEmptyExpression() } as? MapCollection<String, Any>

    // TODO might be extracted
    private fun Any?.unwrapOperationData(data: Any?): Any? =
        when {
            this is List<*> -> map { it.unwrapOperationData(data) }
            isNotEmptyExpression() -> evaluate(this as MapCollection<String, Any?>, data)
            else -> this
        }

    private fun Any?.isNotEmptyExpression() = (this as? MapCollection<*, *>)?.let {
        it.isNotEmpty() && it.keys.all { key -> key is String }
    } ?: false
}
