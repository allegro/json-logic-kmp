package operations.array

import operations.LogicOperation
import kotlin.collections.Map

internal object None : LogicOperation, OccurrenceCheckOperation {
    override val key: String = "none"

    override fun invoke(expression: Any?, data: Any?): Any? = checkOccurrence(expression, data)

    override fun getOperationDefault(mappingOperation: Map<String, Any>?, expressionValues: List<Any?>) = true

    override fun check(
        operationData: List<Any?>,
        mappingOperation: Map<String, Any>,
        operationDefault: Any?
    ): Any? {
        operationData.forEach { dataValue ->
            if (unwrapValueAsBoolean(evaluateLogic(mappingOperation, dataValue))) {
                return@check false
            }
        }
        return operationDefault
    }
}
