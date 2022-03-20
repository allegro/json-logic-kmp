package operations.array

import LogicEvaluator
import operations.FunctionalLogicOperation

internal object All : FunctionalLogicOperation, OccurrenceCheckOperation {
    override fun invoke(expression: Any?, data: Any?, evaluator: LogicEvaluator): Any? =
        checkOccurrence(expression, data, evaluator)

    override fun check(
        data: OccurenceCheckData,
        evaluator: LogicEvaluator
    ) = with(data) {
        operationData.forEach { dataValue ->
            if (unwrapValueAsBoolean(evaluator.evaluateLogic(mappingOperation, dataValue)).not()) {
                return@with operationDefault
            }
        }
        return@with true
    }
}
