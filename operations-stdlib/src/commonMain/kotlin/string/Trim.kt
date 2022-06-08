package string

import operation.StandardLogicOperation
import utils.asList
import utils.secondOrNull
import utils.thirdOrNull

object Trim : StandardLogicOperation, StringUnwrapStrategy {
    override fun evaluateLogic(expression: Any?, data: Any?): Any?  {
        return when (val firstItem = expression.asList.firstOrNull()) {
            is String -> firstItem.trimElements(' ', TrimMode.BothEnds)
            is List<*> -> {
                val text = firstItem.firstOrNull()
                val sign = firstItem.secondOrNull() ?: " "
                val mode = (firstItem.thirdOrNull() as? String).toTrimMode() ?: TrimMode.BothEnds
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

    private fun Any?.trimElements(char: Char, mode: TrimMode) =
        when (this) {
            is String -> {
                modeBasedTrim(
                    mode = mode,
                    start = { trimStart(char) },
                    end = { trimEnd(char) },
                    bothEnds = { trim(char) }
                )
            }
            else -> null
        }

    private fun modeBasedTrim(mode: TrimMode,
                              start: (() -> Any?),
                              end: (() -> Any?),
                              bothEnds: (() -> Any?)
    ) =
        when (mode) {
            TrimMode.Start -> start()
            TrimMode.End -> end()
            TrimMode.BothEnds -> bothEnds()
        }
}

private sealed class TrimMode {
    object Start : TrimMode()
    object End : TrimMode()
    object BothEnds : TrimMode()
}
