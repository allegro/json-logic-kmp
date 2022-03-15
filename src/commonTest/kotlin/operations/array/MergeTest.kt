package operations.array

import LogicOperationTest
import TestInput.Successful

class MergeTest : LogicOperationTest(
    testName = "JsonLogic evaluation with Merge operation",
    successResultTestInput = listOf(
        Successful(
            expression = mapOf("merge" to null),
            resultValue = listOf(null)
        ),
        Successful(
            expression = mapOf("merge" to listOf(1, listOf(2, listOf(3, 4)))),
            resultValue = listOf(1, 2, listOf(3, 4))
        ),
        Successful(
            expression = mapOf("merge" to listOf(1, listOf(2))),
            resultValue = listOf(1, 2)
        ),
        Successful(
            expression = mapOf("merge" to listOf(1, 2)),
            resultValue = listOf(1, 2)
        ),
        Successful(
            expression = mapOf("merge" to 1),
            resultValue = listOf(1)
        ),
        Successful(
            expression = mapOf("merge" to listOf(listOf(1), listOf(2, 3))),
            resultValue = listOf(1, 2, 3)
        ),
        Successful(
            expression = mapOf("merge" to listOf(listOf(1, 2), listOf(3))),
            resultValue = listOf(1, 2, 3)
        ),
        Successful(
            expression = mapOf("merge" to listOf(listOf(1), listOf(2), listOf(3))),
            resultValue = listOf(1, 2, 3)
        ),
        Successful(
            expression = mapOf("merge" to listOf(listOf(1), listOf(2))),
            resultValue = listOf(1, 2)
        ),
        Successful(
            expression = mapOf("merge" to listOf(listOf(1), emptyList())),
            resultValue = listOf(1)
        ),
        Successful(
            expression = mapOf("merge" to listOf(listOf(1))),
            resultValue = listOf(1)
        ),
        Successful(
            expression = mapOf("merge" to emptyList<Any>()),
            resultValue = emptyList<Any>()
        ),
        Successful(
            expression = mapOf("merge" to listOf(1, 2, listOf(3, 4))),
            resultValue = listOf(1, 2, 3, 4)
        ),
        Successful(
            expression = mapOf("merge" to listOf(listOf(1, 2), listOf(3, 4))),
            resultValue = listOf(1, 2, 3, 4)
        )
    )
)
