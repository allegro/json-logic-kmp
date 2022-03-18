import operations.FunctionalLogicOperation
import operations.StandardLogicOperation

internal data class LogicOperations(
    val standardOperations: Map<String, StandardLogicOperation>,
    val functionalOperations: Map<String, FunctionalLogicOperation>
)
