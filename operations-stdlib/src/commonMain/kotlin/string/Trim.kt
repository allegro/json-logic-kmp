package string

import operation.StandardLogicOperation
import utils.asList
import utils.secondOrNull
import utils.thirdOrNull
import utils.toStringOrEmpty

object Trim : StandardLogicOperation, StringUnwrapStrategy {
    override fun evaluateLogic(expression: Any?, data: Any?): Any?  {
        return when (val firstItem = expression.asList.firstOrNull()) {
            is String -> firstItem.trimElements(' ', TrimMode.BothEnds)
            is List<*> -> {
                val text = firstItem.firstOrNull()
                val sign = firstItem.secondOrNull()
                val mode = (firstItem.thirdOrNull() as? String).toTrimMode() ?: return null
                (sign as? String)?.let {
                    val char: Char = it.single()
                    text.trimElements(char, mode)
                }
            }
            else -> null
        }
    }

    private fun String?.toTrimMode() = when (this) {
        "start" -> TrimMode.Start
        "end" -> TrimMode.End
        "bothEnds" -> TrimMode.BothEnds
        else -> null
    }

    private fun Any?.trimElements(char: Char, mode: TrimMode) = (this as? String)?.modeBasedTrim(mode, char)

    private fun String.modeBasedTrim(mode: TrimMode, char: Char) =
        when (mode) {
            TrimMode.Start -> trimStart(char)
            TrimMode.End -> trimEnd(char)
            TrimMode.BothEnds -> trim(char)
        }
}

private sealed class TrimMode {
    object Start : TrimMode()
    object End : TrimMode()
    object BothEnds : TrimMode()
}
