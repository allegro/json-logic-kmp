package operations.array

import FunctionalLogicOperation
import LogicOperations
import operations.LogicOperation
import kotlin.collections.Map

internal class All(operations: LogicOperations) : FunctionalLogicOperation(operations), OccurrenceCheckOperation {
    override val key: String = "all"

    override fun invoke(expression: Any?, data: Any?): Any? = checkOccurrence(expression, data, operations)

    override fun check(
        operationData: List<Any?>,
        mappingOperation: Map<String, Any>,
        operationDefault: Any?
    ): Any? {
        operationData.forEach { dataValue ->
            if (unwrapValueAsBoolean(evaluateLogic(mappingOperation, dataValue, operations)).not()) {
                return@check operationDefault
            }
        }
        return true
    }
}
