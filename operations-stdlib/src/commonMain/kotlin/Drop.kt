import operation.StandardLogicOperation
import utils.asList
import utils.secondOrNull
import utils.thirdOrNull

object Drop : StandardLogicOperation {
    private const val MODE_FIRST = "first"
    private const val MODE_LAST = "last"

    override fun evaluateLogic(expression: Any?, data: Any?): Any? {
        with(expression.asList) {
            val dropCandidate = firstOrNull()
            val count = secondOrNull()
            val mode = thirdOrNull()

            when(dropCandidate) {
                is String ->
                is List<*> ->
                else -> null
            }
        }
    }

    private fun String.drop(count: Int, mode: String) {

    }

    private fun List<*>.drop(count: Int, mode: String) {

    }
}

private enum class DropMode(private val mode: String) {
    FIRST("first"),
    LAST("last")
}
