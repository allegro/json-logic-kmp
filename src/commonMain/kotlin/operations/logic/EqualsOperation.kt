package operations.logic

import operations.ComparingOperation
import operations.logic.unwrap.EqualsUnwrapStrategy
import type.SingleNestedValue
import utils.asList
import utils.secondOrNull

internal interface EqualsOperation : ComparingOperation, EqualsUnwrapStrategy {
    fun compare(values: Any?, operator: (Int, Int) -> Boolean): Boolean =
        with(values.asList) {
            val firstUnwrappedValue = unwrapSingleNestedValueOrDefault(firstOrNull())
            val secondUnwrappedValue = unwrapSingleNestedValueOrDefault(secondOrNull())
            val firstPossibleTrueValues = equalsTableOfTruth[firstUnwrappedValue]
            val secondPossibleTrueValues = equalsTableOfTruth[secondUnwrappedValue]

            when {
                firstPossibleTrueValues != null -> firstPossibleTrueValues.contains(secondUnwrappedValue)
                secondPossibleTrueValues != null -> secondPossibleTrueValues.contains(firstUnwrappedValue)
                else -> compareListOfTwo(map(::unwrapValue), operator)
            }
        }
}

private val equalsTableOfTruth = mapOf(
    true to listOf(true, 1, "1", SingleNestedValue(1), SingleNestedValue("1")),
    false to listOf(
        false,
        0,
        "0",
        "",
        emptyList<Any>(),
        SingleNestedValue(emptyList<Any>()),
        SingleNestedValue(0),
        SingleNestedValue("0"),
        SingleNestedValue(""),
        SingleNestedValue(null)
    ),
    1 to listOf(true, 1, "1", SingleNestedValue(1), SingleNestedValue("1")),
    0 to listOf(
        false,
        0,
        "0",
        "",
        emptyList<Any>(),
        SingleNestedValue(emptyList<Any>()),
        SingleNestedValue(0),
        SingleNestedValue("0"),
        SingleNestedValue(""),
        SingleNestedValue(null)
    ),
    -1 to listOf(-1, "-1", SingleNestedValue(-1), SingleNestedValue("-1")),
    "true" to listOf("true"),
    "false" to listOf("false"),
    "1" to listOf(true, 1, "1", SingleNestedValue(1), SingleNestedValue("1")),
    "0" to listOf(false, 0, "0", SingleNestedValue(0), SingleNestedValue("0")),
    "-1" to listOf(-1, "-1", SingleNestedValue(-1), SingleNestedValue("-1")),
    "" to listOf(
        false,
        0,
        "",
        emptyList<Any>(),
        SingleNestedValue(emptyList<Any>()),
        SingleNestedValue(""),
        SingleNestedValue(null)
    ),
    null to listOf(null),
    emptyList<Any>() to listOf(false, 0, ""),
    SingleNestedValue(null) to listOf(false, 0, ""),
    SingleNestedValue("") to listOf(false, 0, ""),
    SingleNestedValue(emptyList<Any>()) to listOf(false, 0, ""),
    SingleNestedValue(0) to listOf(false, 0, "0"),
    SingleNestedValue(1) to listOf(true, 1, "1")
)
