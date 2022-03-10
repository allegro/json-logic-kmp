package operations.array

import LogicOperationTest
import TestInput

class MergeTest : LogicOperationTest(
    testName = "JsonLogic evaluation with Merge operation",
    successResultTestInput = listOf(
        TestInput(
            expression = mapOf("merge" to null),
            resultValue = listOf(null)
        ),
        TestInput(
            expression = mapOf("merge" to listOf(1, listOf(2, listOf(3, 4)))),
            resultValue = listOf(1, 2, listOf(3, 4))
        ),
        TestInput(
            expression = mapOf("merge" to listOf(1, listOf(2))),
            resultValue = listOf(1, 2)
        ),
        TestInput(
            expression = mapOf("merge" to listOf(1, 2)),
            resultValue = listOf(1, 2)
        ),
        TestInput(
            expression = mapOf("merge" to 1),
            resultValue = listOf(1)
        ),
        TestInput(
            expression = mapOf("merge" to listOf(listOf(1), listOf(2, 3))),
            resultValue = listOf(1, 2, 3)
        ),
        TestInput(
            expression = mapOf("merge" to listOf(listOf(1, 2), listOf(3))),
            resultValue = listOf(1, 2, 3)
        ),
        TestInput(
            expression = mapOf("merge" to listOf(listOf(1), listOf(2), listOf(3))),
            resultValue = listOf(1, 2, 3)
        ),
        TestInput(
            expression = mapOf("merge" to listOf(listOf(1), listOf(2))),
            resultValue = listOf(1, 2)
        ),
        TestInput(
            expression = mapOf("merge" to listOf(listOf(1), emptyList())),
            resultValue = listOf(1)
        ),
        TestInput(
            expression = mapOf("merge" to listOf(listOf(1))),
            resultValue = listOf(1)
        ),
        TestInput(
            expression = mapOf("merge" to emptyList<Any>()),
            resultValue = emptyList<Any>()
        ),
        TestInput(
            expression = mapOf("merge" to listOf(1, 2, listOf(3, 4))),
            resultValue = listOf(1, 2, 3, 4)
        ),
        TestInput(
            expression = mapOf("merge" to listOf(listOf(1, 2), listOf(3, 4))),
            resultValue = listOf(1, 2, 3, 4)
        )
    )
)
