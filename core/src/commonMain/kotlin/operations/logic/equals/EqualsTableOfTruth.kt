package operations.logic.equals

import operations.logic.unwrap.SingleNestedValue

internal object EqualsTableOfTruth{
    private val tableOfTruth = mapOf(
        true to listOf(true, *getAllVariantsOf(1)),
        false to listOf(
            false,
            *getAllVariantsOf(0),
            "",
            emptyList<Any>(),
            SingleNestedValue(emptyList<Any>()),
            SingleNestedValue(""),
            SingleNestedValue(null)
        ),
        1 to listOf(true, *getAllVariantsOf(1)),
        0 to listOf(
            false,
            *getAllVariantsOf(0),
            "",
            emptyList<Any>(),
            SingleNestedValue(emptyList<Any>()),
            SingleNestedValue(""),
            SingleNestedValue(null)
        ),
        "true" to listOf("true"),
        "false" to listOf("false"),
        "1" to listOf(true, 1, 1.0, "1", SingleNestedValue(1), SingleNestedValue(1.0), SingleNestedValue("1")),
        "0" to listOf(false, 0, 0.0, "0", SingleNestedValue(0), SingleNestedValue(0.0), SingleNestedValue("0")),
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
        emptyList<Any>() to listOf(false, 0, 0.0, ""),
        SingleNestedValue(null) to listOf(false, 0, 0.0, ""),
        SingleNestedValue("") to listOf(false, 0, 0.0, ""),
        SingleNestedValue(emptyList<Any>()) to listOf(false, 0, 0.0, ""),
        SingleNestedValue(0) to listOf(false, 0, 0.0, "0"),
        SingleNestedValue(1) to listOf(true, 1, 1.0, "1"),
        SingleNestedValue("1") to listOf(true, 1, 1.0, "1"),
        SingleNestedValue("0") to listOf(false, 0, 0.0, "0"),
        SingleNestedValue(0.0) to listOf(false, 0, 0.0, "0"),
        SingleNestedValue(1.0) to listOf(true, 1, 1.0, "1"),
        SingleNestedValue("1.0") to listOf(true, 1, 1.0),
        SingleNestedValue("0.0") to listOf(false, 0, 0.0),
        1.0 to listOf(1.0, SingleNestedValue(1.0), SingleNestedValue(1), SingleNestedValue("1.0"), "1", 1, true, "1.0"),
        "1.0" to listOf("1.0", 1.0, 1, true),
        0.0 to listOf(*getAllVariantsOf(0.0), false, emptyList<Any>(), SingleNestedValue(emptyList<Any>())),
        "0.0" to listOf("0.0", 0.0, 0, false),
    )

    operator fun get(key: Any?): List<Any?>? = tableOfTruth[key]

    private fun getAllVariantsOf(value: Int) = listOf(value).plusDoubles().plusStrings().plusNested().toTypedArray()
    private fun getAllVariantsOf(value: Double) = listOf(value).plusIntegers().plusStrings().plusNested().toTypedArray()

    private fun List<Any>.plusNested() = plus(map { SingleNestedValue(it) })
    private fun List<Number>.plusDoubles() = plus(map { it.toDouble() })
    private fun List<Number>.plusIntegers() = plus(map { it.toInt() })
    private fun List<Number>.plusStrings() = plus(map { it.toString() })
}
