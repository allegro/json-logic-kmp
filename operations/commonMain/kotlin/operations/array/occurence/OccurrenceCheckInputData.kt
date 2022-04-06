package operations.array.occurence

internal data class OccurrenceCheckInputData(
    val operationData: List<Any?>,
    val mappingOperation: Map<String, Any>,
    val operationDefault: Any?
)
