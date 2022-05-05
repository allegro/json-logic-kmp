import unwrap.EvaluatingUnwrapper
import utils.asList

object Length : FunctionalLogicOperation, EvaluatingUnwrapper, StringUnwrapStrategy2 {
    override fun invoke(expression: Any?, data: Any?, evaluator: LogicEvaluator): Int? {
        val x = unwrapValueAsString(expression)

        val first = expression.asList
        val inputData = first.firstOrNull()

        return when (first.size) {
            1 -> inputData?.toString()?.length
            else -> first.size
        }
    }
}
