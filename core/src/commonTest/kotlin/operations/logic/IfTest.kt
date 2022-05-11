package operations.logic

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import valueShouldBe

class IfTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    withData(
        nameFn = { input -> "Should evaluated ${input.expression} with given ${input.data} result in ${input.result}" },
        ts = listOf(
            TestInput(
                expression = mapOf(
                    "if" to listOf(
                        mapOf("missing" to listOf("a", "b")),
                        "Not enough fruit",
                        "OK to proceed"
                    )
                ),
                data = mapOf("a" to "apple", "b" to "banana"),
                result = JsonLogicResult.Success("OK to proceed")
            ),
            TestInput(
                expression = mapOf("if" to listOf(false, "apple", false, "banana", false, "carrot", "date")),
                result = JsonLogicResult.Success("date")
            ),
            TestInput(
                expression = mapOf("if" to listOf(false, "apple", false, "banana", true, "carrot", "date")),
                result = JsonLogicResult.Success("carrot")
            ),
            TestInput(
                expression = mapOf("if" to listOf(false, "apple", true, "banana", false, "carrot", "date")),
                result = JsonLogicResult.Success("banana")
            ),
            TestInput(
                expression = mapOf("if" to listOf(false, "apple", true, "banana", true, "carrot", "date")),
                result = JsonLogicResult.Success("banana")
            ),
            TestInput(
                expression = mapOf("if" to listOf(true, "apple", false, "banana", false, "carrot", "date")),
                result = JsonLogicResult.Success("apple")
            ),
            TestInput(
                expression = mapOf("if" to listOf(true, "apple", false, "banana", true, "carrot", "date")),
                result = JsonLogicResult.Success("apple")
            ),
            TestInput(
                expression = mapOf("if" to listOf(true, "apple", true, "banana", false, "carrot", "date")),
                result = JsonLogicResult.Success("apple")
            ),
            TestInput(
                expression = mapOf("if" to listOf(true, "apple", true, "banana", true, "carrot", "date")),
                result = JsonLogicResult.Success("apple")
            ),
            TestInput(
                expression = mapOf("if" to listOf(mapOf("var" to "x"), listOf(mapOf("var" to "y")), 99)),
                data = mapOf("x" to true, "y" to 42),
                result = JsonLogicResult.Success(listOf(42))
            ),
            TestInput(
                expression = mapOf("if" to listOf(true, "apple", true, "banana", "carrot")),
                result = JsonLogicResult.Success("apple")
            ),
            TestInput(
                expression = mapOf("if" to listOf(true, "apple", false, "banana", "carrot")),
                result = JsonLogicResult.Success("apple")
            ),
            TestInput(
                expression = mapOf("if" to listOf(false, "apple", true, "banana", "carrot")),
                result = JsonLogicResult.Success("banana")
            ),
            TestInput(
                expression = mapOf("if" to listOf(false, "apple", false, "banana", "carrot")),
                result = JsonLogicResult.Success("carrot")
            ),
            TestInput(
                expression = mapOf("if" to listOf(true, "apple", true, "banana")),
                result = JsonLogicResult.Success("apple")
            ),
            TestInput(
                expression = mapOf("if" to listOf(true, "apple", false, "banana")),
                result = JsonLogicResult.Success("apple")
            ),
            TestInput(
                expression = mapOf("if" to listOf(false, "apple", true, "banana")),
                result = JsonLogicResult.Success("banana")
            ),
            TestInput(
                expression = mapOf("if" to listOf(mapOf("missing" to "a"), "missed it", "found it")),
                data = mapOf("a" to "apple"),
                result = JsonLogicResult.Success("found it")
            ),
            TestInput(
                expression = mapOf("if" to listOf(mapOf("missing" to "a"), "missed it", "found it")),
                data = mapOf("b" to "banana"),
                result = JsonLogicResult.Success("missed it")
            ),
            TestInput(
                expression = mapOf(
                    "if" to listOf(
                        true,
                        mapOf("cat" to listOf("ap", "ple")),
                        mapOf("cat" to listOf("ba", "na", "na"))
                    )
                ), result = JsonLogicResult.Success("apple")
            ),
            TestInput(
                expression = mapOf(
                    "if" to listOf(
                        false,
                        mapOf("cat" to listOf("ap", "ple")),
                        mapOf("cat" to listOf("ba", "na", "na"))
                    )
                ), result = JsonLogicResult.Success("banana")
            ),
            TestInput(
                expression = mapOf("if" to listOf(mapOf(">" to listOf(2, 1)), "apple", "banana")),
                result = JsonLogicResult.Success("apple")
            ),
            TestInput(
                expression = mapOf("if" to listOf(mapOf(">" to listOf(1, 2)), "apple", "banana")),
                result = JsonLogicResult.Success("banana")
            ),
            TestInput(
                expression = mapOf("if" to listOf(true)),
                result = JsonLogicResult.Success(true)
            ),
            TestInput(
                expression = mapOf("if" to listOf(false)),
                result = JsonLogicResult.Success(false)
            ),
            TestInput(
                expression = mapOf("if" to listOf("apple")),
                result = JsonLogicResult.Success("apple")
            ),
            TestInput(
                expression = mapOf("if" to listOf(true, "apple")),
                result = JsonLogicResult.Success("apple")
            ),
            TestInput(
                expression = mapOf("if" to listOf(emptyList<Any>(), "apple", "banana")),
                result = JsonLogicResult.Success("banana")
            ),
            TestInput(
                expression = mapOf("if" to listOf(listOf(1), "apple", "banana")),
                result = JsonLogicResult.Success("apple")
            ),
            TestInput(
                expression = mapOf("if" to listOf(listOf(1, 2, 3, 4), "apple", "banana")),
                result = JsonLogicResult.Success("apple")
            ),
            TestInput(
                expression = mapOf("if" to listOf("", "apple", "banana")),
                result = JsonLogicResult.Success("banana")
            ),
            TestInput(
                expression = mapOf("if" to listOf("zucchini", "apple", "banana")),
                result = JsonLogicResult.Success("apple")
            ),
            TestInput(
                expression = mapOf("if" to listOf("0", "apple", "banana")),
                result = JsonLogicResult.Success("apple")
            ),
            TestInput(
                expression = mapOf("if" to listOf(mapOf("+" to "0"), "apple", "banana")),
                result = JsonLogicResult.Success("banana")
            ),
            TestInput(
                expression = mapOf("if" to listOf(mapOf("+" to "1"), "apple", "banana")),
                result = JsonLogicResult.Success("apple")
            ),
            TestInput(
                expression = mapOf("if" to listOf(0, "apple", "banana")),
                result = JsonLogicResult.Success("banana")
            ),
            TestInput(
                expression = mapOf("if" to listOf(1, "apple", "banana")),
                result = JsonLogicResult.Success("apple")
            ),
            TestInput(
                expression = mapOf("if" to listOf(3.1416, "apple", "banana")),
                result = JsonLogicResult.Success("apple")
            ),
            TestInput(
                expression = mapOf("if" to listOf(-1, "apple", "banana")),
                result = JsonLogicResult.Success("apple")
            ),
            TestInput(
                expression = mapOf("if" to listOf(true, "yes", "no")),
                result = JsonLogicResult.Success("yes")
            ),
            TestInput(
                expression = mapOf("if" to listOf(false, "yes", "no")),
                result = JsonLogicResult.Success("no")
            ),
            TestInput(
                expression = mapOf(
                    "if" to listOf(
                        mapOf("<" to listOf(mapOf("var" to "temp"), 0)), "freezing",
                        mapOf("<" to listOf(mapOf("var" to "temp"), 100)), "liquid",
                        "gas"
                    )
                ),
                data = mapOf("temp" to 55),
                result = JsonLogicResult.Success("liquid")
            ),
            TestInput(
                expression = mapOf("if" to listOf(false, "apple")),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(expression = mapOf("if" to listOf<Any>()), result = JsonLogicResult.Failure.NullResult),
            TestInput(
                expression = mapOf("if" to listOf(false, "apple", false, "banana")),
                result = JsonLogicResult.Failure.NullResult
            ),
            TestInput(
                expression = mapOf("if" to listOf(false, "apple", false, "banana", false, "carrot")),
                result = JsonLogicResult.Failure.NullResult
            )
        )
        // given
    ) { testInput: TestInput ->
        // when
        val evaluationResult = logicEngine.evaluate(testInput.expression, testInput.data)

        // then
        evaluationResult valueShouldBe testInput.result
    }
})
