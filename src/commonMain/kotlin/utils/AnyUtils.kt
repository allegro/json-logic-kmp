package utils

internal val Any?.asList: List<Any?>
    get() = when (this) {
        is String -> listOf(this)
        is List<*> -> this
        else -> listOf()
    }

internal val List<Any?>.comparableList: List<Comparable<*>?>
    get() = map { if (it is Comparable<*>) it else null }

internal val Any?.asComparableList: List<Comparable<*>?>
    get() = asList.comparableList

val Any?.truthy: Boolean
    get() = when (this) {
        null -> false
        is Boolean -> this
        is Number -> toDouble() != 0.0
        is String -> !isEmpty() && this != "[]" && this != "false" && this != "null"
        is Collection<*> -> !isEmpty()
        is Array<*> -> size > 0
        else -> true
    }

// temp
internal val String.unStringify: String
    get() = replace("\"", "")
