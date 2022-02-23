package operations.logic

import utils.asList

private object Undefined

internal interface StrictEqualsOperation : EqualsOperation {
    override fun compare(values: Any?, operator: (Int, Int) -> Boolean): Boolean {
        return with(values.asList) {
            val firstUnwrappedValue = unwrapSingleNestedValueOrDefault(firstOrUndefined())
            val secondUnwrappedValue = unwrapSingleNestedValueOrDefault(secondOrUndefined())
            val firstPossibleTrueValue = strictEqualsTableOfTruth[firstUnwrappedValue]
            val secondPossibleTrueValue = strictEqualsTableOfTruth[secondUnwrappedValue]

            when {
                firstPossibleTrueValue != null -> firstPossibleTrueValue.contains(secondUnwrappedValue)
                secondPossibleTrueValue != null -> secondPossibleTrueValue.contains(firstUnwrappedValue)
                else -> compareListOfTwo(map(::unwrapValue), operator)
            }
        }
    }

    private fun List<Any?>.firstOrUndefined() = if (isNotEmpty()) first() else Undefined
    private fun List<Any?>.secondOrUndefined() = if (size >= 2) get(1) else Undefined

    override fun unwrapValue(wrappedValue: Any?): Any? =
        when (wrappedValue) {
            is Number -> wrappedValue.toDouble()
            else -> wrappedValue
        }

    override fun unwrapValueAsBoolean(wrappedValue: Any?): Boolean? = when (wrappedValue) {
        is Boolean -> wrappedValue
        else -> null
    }
}

private val strictEqualsTableOfTruth = mapOf(
    true to listOf(true),
    false to listOf(false),
    1 to listOf(1),
    0 to listOf(0),
    -1 to listOf(-1),
    "true" to listOf("true"),
    "false" to listOf("false"),
    "1" to listOf("1"),
    "0" to listOf("0"),
    "-1" to listOf("-1"),
    "" to listOf(""),
    null to listOf(null),
    Undefined to listOf(Undefined)
)
