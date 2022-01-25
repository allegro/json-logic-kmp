internal val Any.asString: Any
    get() = when {
        this is String && startsWith("\"") && endsWith("\"") -> this
        this is String && toDoubleOrNull() != null && !contains(".") -> "\"$this\""
        this is String && toDoubleOrNull() != null -> this
        this is String -> "\"$this\""
        else -> this
    }
