package operations.array

import FunctionalLogicOperation
import LogicOperations
import operations.LogicOperation
import kotlin.collections.Map

internal class None(operations: LogicOperations) : FunctionalLogicOperation(operations), OccurrenceCheckOperation {
    override val key: String = "none"

    override fun invoke(expression: Any?, data: Any?): Any? = checkOccurrence(expression, data, operations)

    override fun getOperationDefault(mappingOperation: Map<String, Any>?, expressionValues: List<Any?>) = true

    override fun check(
        operationData: List<Any?>,
        mappingOperation: Map<String, Any>,
        operationDefault: Any?
    ): Any? {
        operationData.forEach { dataValue ->
            if (unwrapValueAsBoolean(evaluateLogic(mappingOperation, dataValue, operations))) {
                return@check false
            }
        }
        return operationDefault
    }
}
