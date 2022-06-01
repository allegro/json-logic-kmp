package array

import operation.StandardLogicOperation
import utils.asList

object JoinToString : StandardLogicOperation {
    override fun evaluateLogic(expression: Any?, data: Any?): Any? = expression.asList.toOperationArguments()?.invoke()

    private fun JoinToStringArguments.invoke() =
        elementsToJoin.joinToString(separator, prefix, postfix, limit, truncated)

    private fun List<Any?>.toOperationArguments(): JoinToStringArguments? = kotlin.runCatching {
        JoinToStringArguments(
            elementsToJoin = get(0).asList,
            separator = get(1) as String,
            prefix = get(2) as String,
            postfix = get(3) as String,
            limit = get(4) as Int,
            truncated = get(5) as String
        )
    }.fold(
        onSuccess = { it },
        onFailure = { null }
    )
}

private data class JoinToStringArguments(
    val elementsToJoin: List<Any?>,
    val separator: String,
    val prefix: String,
    val postfix: String,
    val limit: Int,
    val truncated: String
)
