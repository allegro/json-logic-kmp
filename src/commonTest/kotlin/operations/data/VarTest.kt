package operations.data

import TestInput.Successful
import TestInput.Unsuccessful
import io.kotest.core.spec.style.FunSpec
import testWithFailureResultData
import testWithSuccessResultData

class VarTest : FunSpec({
    context("JsonLogic evaluation with Var operation") {
        testWithSuccessResultData(
            listOf(
                Successful(
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
                    resultValue = "apple"
                ),
                Successful(
                    expression = mapOf("var" to emptyList<Any>()),
                    data = mapOf("a" to "apple", "b" to "banana"),
                    resultValue = mapOf("a" to "apple", "b" to "banana")
                ),
                Successful(
                    expression = mapOf("var" to emptyList<Any>()),
                    data = mapOf("a" to "apple", "b" to listOf("banana", "beer")),
                    resultValue = mapOf("a" to "apple", "b" to listOf("banana", "beer"))
                ),
                Successful(expression = mapOf("var" to listOf("a")), data = mapOf("a" to 1), resultValue = 1),
                Successful(expression = mapOf("var" to "a"), data = mapOf("a" to 1), resultValue = 1),
                Successful(expression = mapOf("var" to listOf("a", 1)), resultValue = 1),
                Successful(expression = mapOf("var" to listOf("a", 1, 2)), resultValue = 1),
                Successful(expression = mapOf("var" to listOf("b", 2)), data = mapOf("a" to 1), resultValue = 2),
                Successful(
                    expression = mapOf("var" to "a.b"),
                    data = mapOf("a" to mapOf("b" to "c")),
                    resultValue = "c"
                ),
                Successful(
                    expression = mapOf("var" to listOf("a.q", 9)),
                    data = mapOf("a" to mapOf("b" to "c")),
                    resultValue = 9
                ),
                Successful(
                    expression = mapOf("var" to listOf("a.b", 9)),
                    data = mapOf("a" to mapOf("b" to "c")),
                    resultValue = "c"
                ),
                Successful(expression = mapOf("var" to 1), data = listOf("apple", "banana"), resultValue = "banana"),
                Successful(expression = mapOf("var" to "1"), data = listOf("apple", "banana"), resultValue = "banana"),
                Successful(
                    expression = mapOf("var" to "1.1"),
                    data = listOf("apple", listOf("banana", "beer")),
                    resultValue = "beer"
                ),

                Successful(expression = mapOf("var" to ""), data = 1, resultValue = 1),
                Successful(
                    expression = mapOf("var" to ""),
                    data = listOf(1, 2, 3, 4),
                    resultValue = listOf(1, 2, 3, 4)
                ),
                Successful(
                    expression = mapOf("var" to emptyList<Any>()),
                    data = listOf(1, 2, 3, 4),
                    resultValue = listOf(1, 2, 3, 4)
                ),
                Successful(
                    expression = mapOf("var" to null),
                    data = listOf(1, 2, 3, 4),
                    resultValue = listOf(1, 2, 3, 4)
                ),
                Successful(
                    expression = mapOf("var" to listOf(null)),
                    data = listOf(1, 2, 3, 4),
                    resultValue = listOf(1, 2, 3, 4)
                ),
                Successful(
                    expression = mapOf("var" to listOf("")),
                    data = listOf(1, 2, 3, 4),
                    resultValue = listOf(1, 2, 3, 4)
                ),
                Successful(expression = mapOf("var" to listOf(1)), data = listOf(1, 2, 3, 4), resultValue = 2),
                Successful(expression = mapOf("var" to listOf(listOf(1))), data = listOf(1, 2, 3, 4), resultValue = 2),
                Successful(
                    expression = mapOf("var" to listOf(listOf(1), 2)),
                    data = listOf(1, 2, 3, 4),
                    resultValue = 2
                ),
                Successful(expression = mapOf("var" to listOf(1, 2)), data = listOf(1, 2, 3, 4), resultValue = 2),
                Successful(expression = mapOf("var" to null), data = 1, resultValue = 1),
                Successful(
                    expression = mapOf("var" to null),
                    data = mapOf("a" to "apple", "b" to "banana"),
                    resultValue = mapOf("a" to "apple", "b" to "banana")
                ),
                Successful(
                    expression = mapOf("var" to null),
                    data = mapOf("a" to "apple", "b" to listOf("banana", "beer")),
                    resultValue = mapOf("a" to "apple", "b" to listOf("banana", "beer"))
                ),
                Successful(
                    expression = mapOf("var" to null),
                    data = listOf("apple", "banana"),
                    resultValue = listOf("apple", "banana")
                ),
                Successful(
                    expression = mapOf("var" to null),
                    data = listOf("apple", 1, null),
                    resultValue = listOf("apple", 1, null)
                ),
                Successful(
                    expression = mapOf("var" to null),
                    data = listOf("apple", listOf("banana", "beer")),
                    resultValue = listOf("apple", listOf("banana", "beer"))
                ),

                Successful(expression = mapOf("var" to emptyList<Any>()), data = 1, resultValue = 1),
                Successful(
                    expression = mapOf("var" to emptyList<Any>()),
                    data = listOf("apple", "banana"),
                    resultValue = listOf("apple", "banana")
                ),
                Successful(
                    expression = mapOf("var" to "1"),
                    data = listOf("apple", listOf("banana", "beer")),
                    resultValue = listOf("banana", "beer")
                ),
            )
        )
        testWithFailureResultData(
            listOf(
                Unsuccessful(expression = mapOf("var" to "b"), data = mapOf("a" to 1)),
                Unsuccessful(expression = mapOf("var" to "a")),
                Unsuccessful(expression = mapOf("var" to listOf(listOf(""))), data = listOf(1, 2, 3, 4)),
                Unsuccessful(expression = mapOf("var" to listOf(listOf(null))), data = listOf(1, 2, 3, 4)),
                Unsuccessful(expression = mapOf("var" to listOf("b")), data = mapOf("a" to 1)),
                Unsuccessful(expression = mapOf("var" to listOf("b"))),
                Unsuccessful(
                    expression = mapOf("var" to listOf(emptyList<Any>())),
                    data = listOf(1, 2, 3, 4)
                ),
                Unsuccessful(expression = mapOf("var" to "a.b.c")),
                Unsuccessful(expression = mapOf("var" to "a.b.c"), data = mapOf("a" to null)),
                Unsuccessful(expression = mapOf("var" to "a.q"), data = mapOf("a" to mapOf("b" to "c"))),

                Unsuccessful(expression = mapOf("var" to "a.b.c"), data = mapOf("a" to mapOf("b" to null))),
            )
        )
    }
})
