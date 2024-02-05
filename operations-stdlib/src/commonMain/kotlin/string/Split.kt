package string

import operation.StandardLogicOperation
import utils.asList

object Split : StandardLogicOperation {
    private const val TEXT_ARG_INDEX = 0
    private const val DELIMITERS_ARG_INDEX = 1

    override fun evaluateLogic(expression: Any?, data: Any?): Any? =
        expression.asList.toOperationArguments()?.invokeSplit()

    private fun List<Any?>.toOperationArguments(): SplitArguments? = runCatching {
        SplitArguments(
            text = get(TEXT_ARG_INDEX) as String,
            delimiters = get(DELIMITERS_ARG_INDEX) as List<Any?>
        )
    }.fold(
        onSuccess = { it },
        onFailure = { null }
    )

    private fun SplitArguments.invokeSplit(): List<String> {
        val convertedDelimiters = delimiters
            .map { it as String }
            .toSet()
            .toTypedArray()
       return text.split(delimiters = convertedDelimiters)
    }
}

private data class SplitArguments(
    var text: String,
    val delimiters: List<Any?>
)
