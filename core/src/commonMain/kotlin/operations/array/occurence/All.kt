package operations.array.occurence

import LogicEvaluator
import operation.FunctionalLogicOperation

internal object All : FunctionalLogicOperation, OccurrenceCheckOperation {
    override fun evaluateLogic(expression: Any?, data: Any?, evaluator: LogicEvaluator): Any? =
        checkOccurrence(expression, data, evaluator)

    override fun check(
        data: OccurrenceCheckInputData,
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
