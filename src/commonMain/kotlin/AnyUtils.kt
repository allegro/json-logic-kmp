internal val Any.asString: Any
    get() = when {
        this is String && startsWith("\"") && endsWith("\"") -> this
        this is String && toDoubleOrNull() != null && !contains(".") -> "\"$this\""
        this is String && toDoubleOrNull() != null -> this
        this is String -> "\"$this\""
        else -> this
    }

internal val Any?.asList: List<Any?>
    get() = when (this) {
        is String ->
            if (startsWith("["))
                replace("[", "").replace("]", "").split(",").map { it.trim() }
            else listOf(this)
        is List<*> -> this
        else -> listOf()
    }
