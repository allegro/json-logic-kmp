package operations.array

import LogicOperationTest
import TestInput

class SomeTest : LogicOperationTest(
    testName = "JsonLogic evaluation with Some operation",
    successResultTestInput = listOf(
        TestInput(
            expression = mapOf(
                "some" to emptyList<Any>()
            ),
            resultValue = false
        ),
        TestInput(
            expression = mapOf(
                "some" to listOf(null)
            ),
            resultValue = false
        ),
        TestInput(
            expression = mapOf(
                "some" to null
            ),
            resultValue = false
        ),
        TestInput(
            expression = mapOf(
                "some" to listOf(mapOf(">=" to listOf(mapOf("var" to ""), 1)))
            ),
            resultValue = false
        ),
        TestInput(
            expression = mapOf(
                "some" to listOf(mapOf("var" to "integers"), mapOf(">=" to listOf(mapOf("var" to ""), 1)))
            ),
            resultValue = false
        ),
        TestInput(
            expression = mapOf(
                "some" to listOf(
                    mapOf(">=" to listOf(mapOf("var" to ""), 1)),
                    mapOf(">=" to listOf(mapOf("var" to ""), 1))
                )
            ),
            resultValue = false
        ),
        TestInput(
            expression = mapOf(
                "some" to listOf(mapOf("var" to "integers"), mapOf(">=" to listOf(mapOf("var" to ""), 1)))
            ),
            data = mapOf("integers" to listOf(1, 2, 3)),
            resultValue = true
        ),
        TestInput(
            expression = mapOf(
                "some" to listOf(mapOf("var" to "integers"), mapOf("==" to listOf(mapOf("var" to ""), 1)))
            ),
            data = mapOf("integers" to listOf(1, 2, 3)),
            resultValue = true
        ),
        TestInput(
            expression = mapOf(
                "some" to listOf(mapOf("var" to "integers"), mapOf("<" to listOf(mapOf("var" to ""), 1)))
            ),
            data = mapOf("integers" to listOf(1, 2, 3)),
            resultValue = false
        ),
        TestInput(
            expression = mapOf(
                "some" to listOf(mapOf("var" to "integers"), mapOf("<" to listOf(mapOf("var" to ""), 1)))
            ),
            data = mapOf("integers" to emptyList<Any>()),
            resultValue = false
        ),
        TestInput(
            expression = mapOf(
                "some" to listOf(mapOf("var" to "items"), mapOf(">=" to listOf(mapOf("var" to "qty"), 1)))
            ),
            data = mapOf("items" to listOf(mapOf("qty" to 1, "sku" to "apple"), mapOf("qty" to 2, "sku" to "banana"))),
            resultValue = true
        ),
        TestInput(
            expression = mapOf(
                "some" to listOf(mapOf("var" to "items"), mapOf(">" to listOf(mapOf("var" to "qty"), 1)))
            ),
            data = mapOf("items" to listOf(mapOf("qty" to 1, "sku" to "apple"), mapOf("qty" to 2, "sku" to "banana"))),
            resultValue = true
        ),
        TestInput(
            expression = mapOf(
                "some" to listOf(mapOf("var" to "items"), mapOf("<" to listOf(mapOf("var" to "qty"), 1)))
            ),
            data = mapOf("items" to listOf(mapOf("qty" to 1, "sku" to "apple"), mapOf("qty" to 2, "sku" to "banana"))),
            resultValue = false
        ),
        TestInput(
            expression = mapOf(
                "some" to listOf(mapOf("var" to "items"), mapOf(">=" to listOf(mapOf("var" to "qty"), 1)))
            ),
            data = mapOf("items" to emptyList<Any>()),
            resultValue = false
        )
    )
)
