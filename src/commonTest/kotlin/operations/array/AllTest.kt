package operations.array

import LogicOperationTest
import TestInput

class AllTest : LogicOperationTest(
    testName = "JsonLogic evaluation with All operation",
    successResultTestInput = listOf(
        TestInput(
            expression = mapOf(
                "all" to listOf(mapOf(">=" to listOf(mapOf("var" to ""), 1)))
            ),
            resultValue = false
        ),
        TestInput(
            expression = mapOf(
                "all" to emptyList<Any>()
            ),
            resultValue = false
        ),
        TestInput(
            expression = mapOf(
                "all" to listOf(null)
            ),
            resultValue = false
        ),
        TestInput(
            expression = mapOf(
                "all" to null
            ),
            resultValue = false
        ),
        TestInput(
            expression = mapOf(
                "all" to listOf(mapOf("var" to "integers"), mapOf(">=" to listOf(mapOf("var" to ""), 1)))
            ),
            resultValue = false
        ),
        TestInput(
            expression = mapOf(
                "all" to listOf(
                    mapOf(">=" to listOf(mapOf("var" to ""), 1)),
                    mapOf(">=" to listOf(mapOf("var" to ""), 1))
                )
            ),
            resultValue = false
        ),
        TestInput(
            expression = mapOf(
                "all" to listOf(mapOf("var" to "integers"), mapOf(">=" to listOf(mapOf("var" to ""), 1)))
            ),
            data = mapOf("integers" to listOf(1, 2, 3)),
            resultValue = true
        ),
        TestInput(
            expression = mapOf(
                "all" to listOf(mapOf("var" to "integers"), mapOf("==" to listOf(mapOf("var" to ""), 1)))
            ),
            data = mapOf("integers" to listOf(1, 2, 3)),
            resultValue = false
        ),
        TestInput(
            expression = mapOf(
                "all" to listOf(mapOf("var" to "integers"), mapOf("<" to listOf(mapOf("var" to ""), 1)))
            ),
            data = mapOf("integers" to listOf(1, 2, 3)),
            resultValue = false
        ),
        TestInput(
            expression = mapOf(
                "all" to listOf(mapOf("var" to "integers"), mapOf("<" to listOf(mapOf("var" to ""), 1)))
            ),
            data = mapOf("integers" to emptyList<Any>()),
            resultValue = false
        ),
        TestInput(
            expression = mapOf(
                "all" to listOf(mapOf("var" to "items"), mapOf(">=" to listOf(mapOf("var" to "qty"), 1)))
            ),
            data = mapOf(
                "items" to listOf(
                    mapOf("qty" to 1, "sku" to "apple"),
                    mapOf("qty" to 2, "sku" to "banana")
                )
            ),
            resultValue = true
        ),
        TestInput(
            expression = mapOf(
                "all" to listOf(mapOf("var" to "items"), mapOf(">" to listOf(mapOf("var" to "qty"), 1)))
            ),
            data = mapOf(
                "items" to listOf(
                    mapOf("qty" to 1, "sku" to "apple"),
                    mapOf("qty" to 2, "sku" to "banana")
                )
            ),
            resultValue = false
        ),
        TestInput(
            expression = mapOf(
                "all" to listOf(mapOf("var" to "items"), mapOf("<" to listOf(mapOf("var" to "qty"), 1)))
            ),
            data = mapOf(
                "items" to listOf(
                    mapOf("qty" to 1, "sku" to "apple"),
                    mapOf("qty" to 2, "sku" to "banana")
                )
            ),
            resultValue = false
        ),
        TestInput(
            expression = mapOf(
                "all" to listOf(mapOf("var" to "items"), mapOf(">=" to listOf(mapOf("var" to "qty"), 1)))
            ),
            data = mapOf("items" to emptyList<Any>()),
            resultValue = false
        )
    )
)
