package utils

import type.JsonLogicList

val Any?.asList: List<Any?>
    get() = (this as? List<*>)?.let {
        JsonLogicList(it)
    } ?: JsonLogicList(listOf(this))

val List<Any?>.comparableList: List<Comparable<*>?>
    get() = asList.map { it.asComparable }

private val Any?.asComparable: Comparable<*>?
    get() = when (this) {
        is Comparable<*> -> this
        is List<*> -> JsonLogicList(this)
        else -> null
    }

val Any?.asDoubleList: List<Double?>
    get() = asList.doubleList

private val List<Any?>.doubleList: List<Double?>
    get() = map {
        when (it) {
            is Number -> it.toDouble()
            is String -> it.toDoubleOrNull()
            else -> null
        }
    }

fun Any?.toStringOrEmpty() = this?.let { toString() }.orEmpty()

fun Any?.isSingleNullList() = this is List<*> && size == 1 && first() == null

fun Any?.isExpression() = (this as? Map<*, *>)?.let {
    it.isNotEmpty() && it.keys.all { key -> key is String }
} ?: false
