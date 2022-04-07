package operations.array

import LogicEvaluator
import utils.asList

internal interface NoInitialValueOperation : ArrayOperation {
    fun invokeArrayOperation(
        expression: Any?,
        operationData: Any?,
        evaluator: LogicEvaluator,
        arrayOperation: (ArrayOperationInputData, LogicEvaluator) -> Any?
    ) = expression.asList.let { expressionValues ->
        val input = createOperationInput(expressionValues, operationData, evaluator)
        arrayOperation(input, evaluator)
    }
}
