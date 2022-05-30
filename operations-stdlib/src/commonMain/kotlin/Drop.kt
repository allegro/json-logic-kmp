import operation.StandardLogicOperation
import utils.asList
import utils.secondOrNull
import utils.thirdOrNull

object Drop : StandardLogicOperation {
    override fun evaluateLogic(expression: Any?, data: Any?): Any? =
        with(expression.asList) {
            val dropCandidate = firstOrNull()
            val count = secondOrNull()
            val mode = (thirdOrNull() as? String).toDropMode()

            (count as? Int)?.let { dropCandidate.drop(it, mode) }
        }

    private fun String?.toDropMode() = when (this) {
        DropMode.FIRST.mode -> DropMode.FIRST
        DropMode.LAST.mode -> DropMode.LAST
        else -> null
    }

    private fun Any?.drop(count: Int, mode: DropMode?) =
        when (this) {
            is String -> modeBasedDrop(mode = mode, first = { drop(count) }, last = { dropLast(count) })
            is List<*> -> modeBasedDrop(mode = mode, first = { drop(count) }, last = { dropLast(count) })
            else -> null
        }

    private fun modeBasedDrop(mode: DropMode?, first: (() -> Any?), last: (() -> Any?)) =
        when (mode) {
            DropMode.FIRST -> first()
            DropMode.LAST -> last()
            else -> null
        }
}

private enum class DropMode(val mode: String) {
    FIRST("first"),
    LAST("last")
}
