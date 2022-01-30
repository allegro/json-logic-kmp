package operations.math

import utils.doubleValue
import utils.truthy
import utils.unStringify

internal interface ComparingExpression {
    fun compare(first: Comparable<*>?, second: Comparable<*>?) = when {
        first is Number && second is Number -> compareValues(first.toDouble(), second.toDouble())
        first is String && second is Number -> compareValues(first.doubleValue, second.toDouble())
        first is Number && second is String -> compareValues(first.toDouble(), second.doubleValue)
        // remove
        first is String && second is String -> compareValues(first.toString().unStringify, second.toString().unStringify)
        first is Boolean || second is Boolean -> compareValues(first.truthy, second.truthy)
        else -> compareValues(first, second)
    }

    fun compareStrict(a: Comparable<*>?, b: Comparable<*>?) = when {
        a is Number && b is Number -> compareValues(a.toDouble(), b.toDouble())
        // remove
        a is String && b is String -> compareValues(a.unStringify, b.unStringify)
        else -> -1
    }
}
