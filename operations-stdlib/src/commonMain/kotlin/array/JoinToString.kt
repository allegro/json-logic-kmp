package array

import operation.StandardLogicOperation
import utils.asList

object JoinToString : StandardLogicOperation {
    private const val ELEMENTS_ARG_INDEX = 0
    private const val SEPARATOR_ARG_INDEX = 1
    private const val PREFIX_ARG_INDEX = 2
    private const val POSTFIX_ARG_INDEX = 3
    private const val LIMIT_ARG_INDEX = 4
    private const val TRUNCATED_ARG_INDEX = 5

    override fun evaluateLogic(expression: Any?, data: Any?): Any? = expression.asList.toOperationArguments()?.join()

    private fun List<Any?>.toOperationArguments(): JoinToStringArguments? = runCatching {
        checkLimitArg()?.let { limit ->
            JoinToStringArguments(
                elementsToJoin = get(ELEMENTS_ARG_INDEX).asList,
                separator = get(SEPARATOR_ARG_INDEX) as String,
                prefix = get(PREFIX_ARG_INDEX) as String,
                postfix = get(POSTFIX_ARG_INDEX) as String,
                limit = limit,
                truncated = get(TRUNCATED_ARG_INDEX) as String
            )
        }
    }.fold(
        onSuccess = { it },
        onFailure = { null }
    )

    private fun List<Any?>.checkLimitArg() = (get(LIMIT_ARG_INDEX) as Number).takeIf {
        it.toDouble() == it.toInt().toDouble()
    }?.toInt()

    private fun JoinToStringArguments.join() =
        elementsToJoin.joinToString(separator, prefix, postfix, limit, truncated)
}

private data class JoinToStringArguments(
    val elementsToJoin: List<Any?>,
    val separator: String,
    val prefix: String,
    val postfix: String,
    val limit: Int,
    val truncated: String
)
