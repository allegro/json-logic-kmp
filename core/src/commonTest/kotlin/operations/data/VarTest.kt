package operations.data

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import testWithInputData

class VarTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with Var operation") {
        testWithInputData(
            logicEngine = logicEngine,
            data = listOf(
                TestInput(
                    expression = mapOf("var" to "b"),
                    data = mapOf("a" to 1),
                    result = JsonLogicResult.NullResultFailure
                ),
                TestInput(expression = mapOf("var" to "a"), result = JsonLogicResult.NullResultFailure),
                TestInput(
                    expression = mapOf("var" to listOf(listOf(""))),
                    data = listOf(1, 2, 3, 4),
                    result = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf("var" to listOf(listOf(null))),
                    data = listOf(1, 2, 3, 4),
                    result = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf("var" to listOf("b")),
                    data = mapOf("a" to 1),
                    result = JsonLogicResult.NullResultFailure
                ),
                TestInput(expression = mapOf("var" to listOf("b")), result = JsonLogicResult.NullResultFailure),
                TestInput(
                    expression = mapOf("var" to listOf(emptyList<Any>())),
                    data = listOf(1, 2, 3, 4),
                    result = JsonLogicResult.NullResultFailure
                ),
                TestInput(expression = mapOf("var" to "a.b.c"), result = JsonLogicResult.NullResultFailure),
                TestInput(
                    expression = mapOf("var" to "a.b.c"),
                    data = mapOf("a" to null),
                    result = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf("var" to "a.q"),
                    data = mapOf("a" to mapOf("b" to "c")),
                    result = JsonLogicResult.NullResultFailure
                ),

                TestInput(
                    expression = mapOf("var" to "a.b.c"),
                    data = mapOf("a" to mapOf("b" to null)),
                    result = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf(
                        "var" to listOf(
                            mapOf(
                                "if" to listOf(
                                    mapOf("<" to listOf(mapOf("var" to "temp"), 110)), "pie.filling", "pie.eta"
                                )
                            )
                        )
                    ),
                    data = mapOf("temp" to 100, "pie" to mapOf("filling" to "apple", "eta" to "60s")),
                    result = JsonLogicResult.Success("apple")
                ),
                TestInput(
                    expression = mapOf("var" to emptyList<Any>()),
                    data = mapOf("a" to "apple", "b" to "banana"),
                    result = JsonLogicResult.Success(mapOf("a" to "apple", "b" to "banana"))
                ),
                TestInput(
                    expression = mapOf("var" to emptyList<Any>()),
                    data = mapOf("a" to "apple", "b" to listOf("banana", "beer")),
                    result = JsonLogicResult.Success(mapOf("a" to "apple", "b" to listOf("banana", "beer")))
                ),
                TestInput(
                    expression = mapOf("var" to listOf("a")),
                    data = mapOf("a" to 1),
                    result = JsonLogicResult.Success(1)
                ),
                TestInput(
                    expression = mapOf("var" to "a"),
                    data = mapOf("a" to 1),
                    result = JsonLogicResult.Success(1)
                ),
                TestInput(expression = mapOf("var" to listOf("a", 1)), result = JsonLogicResult.Success(1)),
                TestInput(expression = mapOf("var" to listOf("a", 1, 2)), result = JsonLogicResult.Success(1)),
                TestInput(
                    expression = mapOf("var" to listOf("b", 2)),
                    data = mapOf("a" to 1),
                    result = JsonLogicResult.Success(2)
                ),
                TestInput(
                    expression = mapOf("var" to "a.b"),
                    data = mapOf("a" to mapOf("b" to "c")),
                    result = JsonLogicResult.Success("c")
                ),
                TestInput(
                    expression = mapOf("var" to listOf("a.q", 9)),
                    data = mapOf("a" to mapOf("b" to "c")),
                    result = JsonLogicResult.Success(9)
                ),
                TestInput(
                    expression = mapOf("var" to listOf("a.b", 9)),
                    data = mapOf("a" to mapOf("b" to "c")),
                    result = JsonLogicResult.Success("c")
                ),
                TestInput(
                    expression = mapOf("var" to 1),
                    data = listOf("apple", "banana"),
                    result = JsonLogicResult.Success("banana")
                ),
                TestInput(
                    expression = mapOf("var" to "1"),
                    data = listOf("apple", "banana"),
                    result = JsonLogicResult.Success("banana")
                ),
                TestInput(
                    expression = mapOf("var" to "1.1"),
                    data = listOf("apple", listOf("banana", "beer")),
                    result = JsonLogicResult.Success("beer")
                ),

                TestInput(expression = mapOf("var" to ""), data = 1, result = JsonLogicResult.Success(1)),
                TestInput(
                    expression = mapOf("var" to ""),
                    data = listOf(1, 2, 3, 4),
                    result = JsonLogicResult.Success(listOf(1, 2, 3, 4))
                ),
                TestInput(
                    expression = mapOf("var" to emptyList<Any>()),
                    data = listOf(1, 2, 3, 4),
                    result = JsonLogicResult.Success(listOf(1, 2, 3, 4))
                ),
                TestInput(
                    expression = mapOf("var" to null),
                    data = listOf(1, 2, 3, 4),
                    result = JsonLogicResult.Success(listOf(1, 2, 3, 4))
                ),
                TestInput(
                    expression = mapOf("var" to listOf(null)),
                    data = listOf(1, 2, 3, 4),
                    result = JsonLogicResult.Success(listOf(1, 2, 3, 4))
                ),
                TestInput(
                    expression = mapOf("var" to listOf("")),
                    data = listOf(1, 2, 3, 4),
                    result = JsonLogicResult.Success(listOf(1, 2, 3, 4))
                ),
                TestInput(
                    expression = mapOf("var" to listOf(1)),
                    data = listOf(1, 2, 3, 4),
                    result = JsonLogicResult.Success(2)
                ),
                TestInput(
                    expression = mapOf("var" to listOf(listOf(1))),
                    data = listOf(1, 2, 3, 4),
                    result = JsonLogicResult.Success(2)
                ),
                TestInput(
                    expression = mapOf("var" to listOf(listOf(1), 2)),
                    data = listOf(1, 2, 3, 4),
                    result = JsonLogicResult.Success(2)
                ),
                TestInput(
                    expression = mapOf("var" to listOf(1, 2)),
                    data = listOf(1, 2, 3, 4),
                    result = JsonLogicResult.Success(2)
                ),
                TestInput(expression = mapOf("var" to null), data = 1, result = JsonLogicResult.Success(1)),
                TestInput(
                    expression = mapOf("var" to null),
                    data = mapOf("a" to "apple", "b" to "banana"),
                    result = JsonLogicResult.Success(mapOf("a" to "apple", "b" to "banana"))
                ),
                TestInput(
                    expression = mapOf("var" to null),
                    data = mapOf("a" to "apple", "b" to listOf("banana", "beer")),
                    result = JsonLogicResult.Success(mapOf("a" to "apple", "b" to listOf("banana", "beer")))
                ),
                TestInput(
                    expression = mapOf("var" to null),
                    data = listOf("apple", "banana"),
                    result = JsonLogicResult.Success(listOf("apple", "banana"))
                ),
                TestInput(
                    expression = mapOf("var" to null),
                    data = listOf("apple", 1, null),
                    result = JsonLogicResult.Success(listOf("apple", 1, null))
                ),
                TestInput(
                    expression = mapOf("var" to null),
                    data = listOf("apple", listOf("banana", "beer")),
                    result = JsonLogicResult.Success(listOf("apple", listOf("banana", "beer")))
                ),
                TestInput(
                    expression = mapOf("var" to emptyList<Any>()),
                    data = 1,
                    result = JsonLogicResult.Success(1)
                ),
                TestInput(
                    expression = mapOf("var" to emptyList<Any>()),
                    data = listOf("apple", "banana"),
                    result = JsonLogicResult.Success(listOf("apple", "banana"))
                ),
                TestInput(
                    expression = mapOf("var" to "1"),
                    data = listOf("apple", listOf("banana", "beer")),
                    result = JsonLogicResult.Success(listOf("banana", "beer"))
                ),
            )
        )
    }
})
