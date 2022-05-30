import JsonLogicResult.Failure
import JsonLogicResult.Success
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class DropTest : FunSpec({
    val operatorName = "drop"
    val logicEngine = JsonLogicEngine.Builder()
        .addStandardOperation(operatorName, Drop)
        .build()

    withData(
        nameFn = { input -> "Should evaluated ${input.expression} with given ${input.data} result in ${input.result}" },
        ts = listOf(
            // TODO add some more complex cases
            TestInput(
                expression = mapOf(operatorName to listOf(listOf(1, 2, 3, 4), 2, "last")),
                result = Success(listOf(1, 2))
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(listOf(1, 2, 3, 4), 0, "last")),
                result = Success(listOf(1, 2, 3, 4))
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(listOf(1, 2, 3, 4), 2, "first")),
                result = Success(listOf(3, 4))
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(listOf(1, 2, 3, 4), null, "first")),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(listOf(1, 2, 3, 4), 2)),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(listOf(1, 2, 3, 4), "2", "first")),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(listOf(1, 2, 3, 4))),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(listOf(true, false, false), 2, "first")),
                result = Success(listOf(false))
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(listOf(null, null, null), 1, "first")),
                result = Success(listOf(null, null))
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(listOf("banana", "strawberry", "spinach"), 1, "last")),
                result = Success(listOf("banana", "strawberry"))
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(listOf("banana", "strawberry", "spinach"), 3, "last")),
                result = Success(emptyList<String>())
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(listOf("banana", "strawberry", "spinach"), 3, "first")),
                result = Success(emptyList<String>())
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(listOf("banana", 1, true), 2, "first")),
                result = Success(listOf(true))
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(
                        listOf(listOf("banana"), listOf("apple"), listOf("pear")),
                        1,
                        "last"
                    )
                ),
                result = Success(listOf(listOf("banana"), listOf("apple")))
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(
                        mapOf(
                            operatorName to listOf(
                                listOf(
                                    "pear",
                                    "cabbage",
                                    "courgette"
                                ), 1, "first"
                            )
                        ), 1, "last"
                    )
                ),
                result = Success(listOf("cabbage"))
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf(
                        mapOf(
                            operatorName to listOf(
                                "101", 1, "first"
                            )
                        ), 1, "last"
                    )
                ),
                result = Success("0")
            ),
            TestInput(
                expression = mapOf(operatorName to listOf("12345", 2, "first")),
                result = Success("345")
            ),
            TestInput(
                expression = mapOf(operatorName to listOf("12345", 0, "first")),
                result = Success("12345")
            ),
            TestInput(
                expression = mapOf(operatorName to listOf("", 5, "first")),
                result = Success("")
            ),
            TestInput(
                expression = mapOf(operatorName to listOf("     ", 4, "first")),
                result = Success(" ")
            ),
            TestInput(
                expression = mapOf(operatorName to listOf("12345", 2, "last")),
                result = Success("123")
            ),
            TestInput(
                expression = mapOf(operatorName to mapOf("var" to "key")),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to 1.3),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to null),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to true),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to emptyList<Any>()),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(mapOf("var" to "key"), 2, "last")),
                data = mapOf("key" to listOf(1, 2, 3, 4, 5)),
                result = Success(listOf(1, 2, 3))
            ),
        )
        // given
    ) { testInput: TestInput ->
        // when
        val evaluationResult = logicEngine.evaluate(testInput.expression, testInput.data)

        // then
        evaluationResult shouldBe testInput.result
    }
})
