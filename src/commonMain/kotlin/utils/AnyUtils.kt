package utils

import type.JsonLogicList

internal val Any?.asList: List<Any?>
    get() = (this as? List<*>)?.let {
        JsonLogicList(it)
    } ?: JsonLogicList(listOf(this))

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

internal val Any?.truthy: Boolean
    get() = when (this) {
        null -> false
        is Boolean -> this
        is Number -> toDouble() != 0.0
        is String -> isNotEmpty() && this != "[]" && this != "null"
        is Collection<*> -> isNotEmpty()
        is Array<*> -> isNotEmpty()
        else -> true
    }
