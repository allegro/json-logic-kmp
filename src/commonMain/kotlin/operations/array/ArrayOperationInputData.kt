package operations.array

import kotlin.collections.Map

internal data class ArrayOperationInputData(
    val operationData: List<Any?>?,
    val mappingOperation: Map<String, Any>?,
    val operationDefault: Any?,
)
