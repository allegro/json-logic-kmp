package evaluation

import operation.FunctionalLogicOperation
import operation.StandardLogicOperation

internal data class LogicOperations(
    val standardOperations: Map<String, StandardLogicOperation> = emptyMap(),
    val functionalOperations: Map<String, FunctionalLogicOperation> = emptyMap()
)
