import expressions.Missing
import expressions.MissingSome
import expressions.Var
import expressions.Var.operation

internal class CommonJsonLogicEngine : JsonLogicEngine {
    private val operations: Map<String, (Any?, Any?) -> Any?> = mapOf(
        Var.operation,
        MissingSome.operation,
        Missing.operation
    )

    override fun evaluate(expression: String, data: Map<String, Any>): String {
        val a =
        return "SUCCESS"
    }
}
