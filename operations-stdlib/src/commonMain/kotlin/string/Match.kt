package string

import operation.StandardLogicOperation
import utils.asList

object Match : StandardLogicOperation, StringUnwrapStrategy {
    private const val TEXT_ARG_INDEX = 0
    private const val REGEX_PATTERN_ARG_INDEX = 1
    private const val REGEX_OPTIONS_ARG_INDEX = 2
    override fun evaluateLogic(expression: Any?, data: Any?): Any? =
        expression.asList.toOperationArguments()?.invokeRegex()

    private fun List<Any?>.toOperationArguments(): MatchArguments? = runCatching {

        MatchArguments(
            text = get(TEXT_ARG_INDEX) as String,
            regexPattern = get(REGEX_PATTERN_ARG_INDEX) as String,
            regexOptions = get(REGEX_OPTIONS_ARG_INDEX) as List<Any?>
        )
    }.fold(
        onSuccess = { it },
        onFailure = { null }
    )

    private fun convertArrayToRegexOptions(options: List<Any?>): Set<RegexOption> {
        return options.map { RegexOption.valueOf(it as String) }.toSet()
    }

    private fun matchBasic(regexPattern: String, text: String): Boolean = regexPattern.toRegex().matches(text)

    private fun matchWithOptions(options: List<Any?>, regexPattern: String, text: String): Boolean {
        val convertedOptions = convertArrayToRegexOptions(options)
        val regex = regexPattern.toRegex(convertedOptions)
        return if(convertedOptions.any { it == RegexOption.MULTILINE}) {
            val splittedString = text.split("\n")
            return splittedString.all { regex.matches(it) }
        } else {
            regex.matches(text)
        }
    }

    private fun MatchArguments.invokeRegex() = if (regexOptions.isNullOrEmpty()) matchBasic(regexPattern, text)
    else matchWithOptions(regexOptions, regexPattern, text)
}

private data class MatchArguments(
    val text: String,
    val regexPattern: String,
    val regexOptions: List<Any?>,
)
