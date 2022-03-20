package operations.array

import LogicEvaluator
import operations.FunctionalLogicOperation
import kotlin.collections.Map

internal object None : FunctionalLogicOperation, OccurrenceCheckOperation {
    override fun invoke(expression: Any?, data: Any?, evaluator: LogicEvaluator): Any? =
        checkOccurrence(expression, data, evaluator)

    override fun getOperationDefault(mappingOperation: Map<String, Any>?, expressionValues: List<Any?>) = true

    override fun check(
        data: OccurenceCheckData,
        evaluator: LogicEvaluator
    ) = with(data) {
        operationData.forEach { dataValue ->
            if (unwrapValueAsBoolean(evaluator.evaluateLogic(mappingOperation, dataValue))) {
                return@with false
            }
        }
        return@with operationDefault
    }
}
