package evaluation

import operations.FunctionalLogicOperation
import operations.StandardLogicOperation

internal data class LogicOperations(
    val standardOperations: Map<String, StandardLogicOperation> = emptyMap(),
    val functionalOperations: Map<String, FunctionalLogicOperation> = emptyMap()
)
