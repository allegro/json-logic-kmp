package operations.array

import JsonLogicEngine
import kotlin.collections.Map

internal interface ArrayOperation {
    fun evaluate(expression: Map<String, Any?>, data: Any?) = JsonLogicEngine.instance.evaluate(expression, data)
}
