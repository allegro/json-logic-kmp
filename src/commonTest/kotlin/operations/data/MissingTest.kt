package operations.data

import LogicOperationTest
import TestInput

class MissingTest : LogicOperationTest(
    testName = "JsonLogic evaluation with only Missing operation",
    successResultTestInput = listOf(
        TestInput(
            expression = mapOf("missing" to listOf("a")),
            data = mapOf("a" to ""),
            resultValue = listOf("a")
        ),
        TestInput(
            expression = mapOf("missing" to listOf("a")),
            data = mapOf("a" to emptyList<Any>()),
            resultValue = emptyList<Any>()
        ),
        TestInput(
            expression = mapOf("missing" to listOf("a")),
            data = mapOf("a" to null),
            resultValue = listOf("a")
        ),
        TestInput(
            expression = mapOf("missing" to listOf("a")),
            data = mapOf("a" to 0),
            resultValue = emptyList<Any>()
        ),
        TestInput(
            expression = mapOf("missing" to listOf("a")),
            data = mapOf("a" to "0"),
            resultValue = emptyList<Any>()
        ),
        TestInput(
            expression = mapOf("missing" to listOf("a")),
            data = mapOf("a" to listOf(null)),
            resultValue = emptyList<Any>()
        ),
        TestInput(
            expression = mapOf("missing" to listOf("a")),
            data = mapOf("a" to listOf("")),
            resultValue = emptyList<Any>()
        ),
        TestInput(
            expression = mapOf("missing" to emptyList<Any>()),
            resultValue = emptyList<Any>()
        ),
        TestInput(
            expression = mapOf("missing" to listOf("a")),
            resultValue = listOf("a")
        ),
        TestInput(
            expression = mapOf("missing" to "a"),
            data = mapOf("a" to "apple"),
            resultValue = emptyList<Any>()
        ),
        TestInput(
            expression = mapOf("missing" to listOf("a")),
            data = mapOf("a" to "apple"),
            resultValue = emptyList<Any>()
        ),
        TestInput(
            expression = mapOf("missing" to listOf("a", "b")),
            data = mapOf("a" to "apple"),
            resultValue = listOf("b")
        ),
        TestInput(
            expression = mapOf("missing" to listOf("a", "b")),
            data = mapOf("a" to "apple", "b" to "banana"),
            resultValue = emptyList<Any>()
        ),
        TestInput(
            expression = mapOf("missing" to listOf("a", "b")),
            data = emptyMap<String, Any>(),
            resultValue = listOf("a", "b")
        ),
        TestInput(
            expression = mapOf("missing" to listOf("a", "b")),
            resultValue = listOf("a", "b")
        ),
        TestInput(
            expression = mapOf("missing" to listOf("a.b")),
            resultValue = listOf("a.b")
        ),
        TestInput(
            expression = mapOf("missing" to listOf("a.b")),
            data = mapOf("a" to "apple"),
            resultValue = listOf("a.b")
        ),
        TestInput(
            expression = mapOf("missing" to listOf("a.b")),
            data = mapOf("a" to mapOf("c" to "apple cake")),
            resultValue = listOf("a.b")
        ),
        TestInput(
            expression = mapOf("missing" to listOf("a.b")),
            data = mapOf("a" to mapOf("b" to "apple brownie")),
            resultValue = emptyList<Any>()
        ),
        TestInput(
            expression = mapOf("missing" to listOf("a.b", "a.c")),
            data = mapOf("a" to mapOf("b" to "apple brownie")),
            resultValue = listOf("a.c")
        ),
        TestInput(
            expression = mapOf(
                "missing" to mapOf(
                    "merge" to listOf(
                        "vin", mapOf(
                            "if" to listOf(
                                mapOf("var" to "financing"), listOf("apr"), emptyList<Any>()
                            )
                        )
                    )
                )
            ),
            data = mapOf("financing" to true),
            resultValue = listOf(
                "vin",
                "apr"
            )
        ),
        TestInput(
            expression = mapOf(
                "missing" to mapOf(
                    "merge" to listOf(
                        "vin", mapOf(
                            "if" to listOf(
                                mapOf("var" to "financing"), listOf("apr"), emptyList<Any>()
                            )
                        )
                    )
                )
            ),
            data = mapOf("financing" to false),
            resultValue = listOf("vin")
        )
    )
)
