import type.JsonLogicList

internal val Any?.asList: List<Any?>
    get() = when (this) {
        is List<*> -> JsonLogicList(this)
        else -> JsonLogicList(listOf(this))
    }

internal val List<Any?>.comparableList: List<Comparable<*>?>
    get() = asList.map { it.asComparable }

private val Any?.asComparable: Comparable<*>?
    get() = when (this) {
        is Comparable<*> -> this
        is List<*> -> JsonLogicList(this)
        else -> null
    }

internal val Any?.asDoubleList: List<Double?>
    get() = asList.doubleList

private val List<Any?>.doubleList: List<Double?>
    get() = map {
        when (it) {
            is Number -> it.toDouble()
            is String -> it.toDoubleOrNull()
            else -> null
        }
    }

internal fun Any?.toStringOrEmpty() = this?.let { toString() }.orEmpty()
