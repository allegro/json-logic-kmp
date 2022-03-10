package operations.data

import LogicOperationTest
import TestInput

class MissingSomeTest : LogicOperationTest(
    testName = "JsonLogic evaluation with only MissingSome operation",
    successResultTestInput = listOf(
        TestInput(
            expression = mapOf("missing_some" to listOf(1, listOf("a", "b"))),
            data = mapOf("a" to "apple"),
            resultValue = emptyList<Any>()
        ),
        TestInput(
            expression = mapOf("missing_some" to listOf(1, listOf("a", "b"))),
            data = mapOf("b" to "banana"),
            resultValue = emptyList<Any>()
        ),
        TestInput(
            expression = mapOf("missing_some" to listOf(1, listOf("a", "b"))),
            data = mapOf("a" to "apple", "b" to "banana"),
            resultValue = emptyList<Any>()
        ),
        TestInput(
            expression = mapOf("missing_some" to listOf(1, listOf("a", "b"))),
            data = mapOf("c" to "carrot"),
            resultValue = listOf("a", "b")
        ),
        TestInput(
            expression = mapOf("missing_some" to listOf(2, listOf("a", "b", "c"))),
            data = mapOf("a" to "apple", "b" to "banana"),
            resultValue = emptyList<Any>()
        ),
        TestInput(
            expression = mapOf("missing_some" to listOf(2, listOf("a", "b", "c"))),
            data = mapOf("a" to "apple", "c" to "carrot"),
            resultValue = emptyList<Any>()
        ),
        TestInput(
            expression = mapOf("missing_some" to listOf(2, listOf("a", "b", "c"))),
            data = mapOf("a" to "apple", "b" to "banana", "c" to "carrot"),
            resultValue = emptyList<Any>()
        ),
        TestInput(
            expression = mapOf("missing_some" to listOf(2, listOf("a", "b", "c"))),
            data = mapOf("a" to "apple", "d" to "durian"),
            resultValue = listOf("b", "c")
        ),
        TestInput(
            expression = mapOf("missing_some" to listOf(2, listOf("a", "b", "c"))),
            data = mapOf("d" to "durian", "e" to "eggplant"),
            resultValue = listOf("a", "b", "c")
        ),
    )
)
