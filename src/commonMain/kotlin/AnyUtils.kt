internal val Any?.asList: List<Any?>
    get() = when (this) {
        is String -> listOf(this)
        is List<*> -> this
        else -> listOf()
    }
