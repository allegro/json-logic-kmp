package utils

fun <T>List<T>.secondOrNull() = getOrNull(1)
fun <T>List<T>.thirdOrNull() = getOrNull(2)

@Suppress("UNCHECKED_CAST")
fun List<Any?>.getMappingOperationOrNull() = secondOrNull().takeIf { it.isExpression() } as? Map<String, Any>
