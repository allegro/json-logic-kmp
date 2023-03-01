package string

import operation.StandardLogicOperation
import utils.asList

object Match : StandardLogicOperation, StringUnwrapStrategy {
    private const val TEXT_ARG_INDEX = 0
    private const val REGEX_PATTERN_ARG_INDEX = 1
    override fun evaluateLogic(expression: Any?, data: Any?): Any? =
        expression.asList.toOperationArguments()?.invokeRegex()

    private fun List<Any?>.toOperationArguments(): MatchArguments? = runCatching {
        MatchArguments(
            text = get(TEXT_ARG_INDEX) as String,
            regexPattern = get(REGEX_PATTERN_ARG_INDEX) as String,
        )
    }.fold(
        onSuccess = { it },
        onFailure = { null }
    )

    private fun MatchArguments.invokeRegex() = regexPattern.toRegex().matches(text)
    
}

private data class MatchArguments(
    val text: String,
    val regexPattern: String,
)
