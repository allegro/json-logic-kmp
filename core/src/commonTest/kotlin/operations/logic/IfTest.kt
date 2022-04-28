package operations.logic

import JsonLogicEngine
import JsonLogicResult
import TestInput
import io.kotest.core.spec.style.FunSpec
import testWithInputData

class IfTest : FunSpec({
    val logicEngine = JsonLogicEngine.Builder().build()

    context("JsonLogic evaluation with If operation") {
        testWithInputData(
            logicEngine = logicEngine,
            data = listOf(
                TestInput(
                    expression = mapOf(
                        "if" to listOf(
                            mapOf("missing" to listOf("a", "b")),
                            "Not enough fruit",
                            "OK to proceed"
                        )
                    ),
                    data = mapOf("a" to "apple", "b" to "banana"),
                    resultValue = JsonLogicResult.Success("OK to proceed")
                ),
                TestInput(
                    expression = mapOf("if" to listOf(false, "apple", false, "banana", false, "carrot", "date")),
                    resultValue = JsonLogicResult.Success("date")
                ),
                TestInput(
                    expression = mapOf("if" to listOf(false, "apple", false, "banana", true, "carrot", "date")),
                    resultValue = JsonLogicResult.Success("carrot")
                ),
                TestInput(
                    expression = mapOf("if" to listOf(false, "apple", true, "banana", false, "carrot", "date")),
                    resultValue = JsonLogicResult.Success("banana")
                ),
                TestInput(
                    expression = mapOf("if" to listOf(false, "apple", true, "banana", true, "carrot", "date")),
                    resultValue = JsonLogicResult.Success("banana")
                ),
                TestInput(
                    expression = mapOf("if" to listOf(true, "apple", false, "banana", false, "carrot", "date")),
                    resultValue = JsonLogicResult.Success("apple")
                ),
                TestInput(
                    expression = mapOf("if" to listOf(true, "apple", false, "banana", true, "carrot", "date")),
                    resultValue = JsonLogicResult.Success("apple")
                ),
                TestInput(
                    expression = mapOf("if" to listOf(true, "apple", true, "banana", false, "carrot", "date")),
                    resultValue = JsonLogicResult.Success("apple")
                ),
                TestInput(
                    expression = mapOf("if" to listOf(true, "apple", true, "banana", true, "carrot", "date")),
                    resultValue = JsonLogicResult.Success("apple")
                ),
                TestInput(
                    expression = mapOf("if" to listOf(mapOf("var" to "x"), listOf(mapOf("var" to "y")), 99)),
                    data = mapOf("x" to true, "y" to 42),
                    resultValue = JsonLogicResult.Success(listOf(42))
                ),
                TestInput(
                    expression = mapOf("if" to listOf(true, "apple", true, "banana", "carrot")),
                    resultValue = JsonLogicResult.Success("apple")
                ),
                TestInput(
                    expression = mapOf("if" to listOf(true, "apple", false, "banana", "carrot")),
                    resultValue = JsonLogicResult.Success("apple")
                ),
                TestInput(
                    expression = mapOf("if" to listOf(false, "apple", true, "banana", "carrot")),
                    resultValue = JsonLogicResult.Success("banana")
                ),
                TestInput(
                    expression = mapOf("if" to listOf(false, "apple", false, "banana", "carrot")),
                    resultValue = JsonLogicResult.Success("carrot")
                ),
                TestInput(
                    expression = mapOf("if" to listOf(true, "apple", true, "banana")),
                    resultValue = JsonLogicResult.Success("apple")
                ),
                TestInput(
                    expression = mapOf("if" to listOf(true, "apple", false, "banana")),
                    resultValue = JsonLogicResult.Success("apple")
                ),
                TestInput(
                    expression = mapOf("if" to listOf(false, "apple", true, "banana")),
                    resultValue = JsonLogicResult.Success("banana")
                ),
                TestInput(
                    expression = mapOf("if" to listOf(mapOf("missing" to "a"), "missed it", "found it")),
                    data = mapOf("a" to "apple"),
                    resultValue = JsonLogicResult.Success("found it")
                ),
                TestInput(
                    expression = mapOf("if" to listOf(mapOf("missing" to "a"), "missed it", "found it")),
                    data = mapOf("b" to "banana"),
                    resultValue = JsonLogicResult.Success("missed it")
                ),
                TestInput(
                    expression = mapOf(
                        "if" to listOf(
                            true,
                            mapOf("cat" to listOf("ap", "ple")),
                            mapOf("cat" to listOf("ba", "na", "na"))
                        )
                    ), resultValue = JsonLogicResult.Success("apple")
                ),
                TestInput(
                    expression = mapOf(
                        "if" to listOf(
                            false,
                            mapOf("cat" to listOf("ap", "ple")),
                            mapOf("cat" to listOf("ba", "na", "na"))
                        )
                    ), resultValue = JsonLogicResult.Success("banana")
                ),
                TestInput(
                    expression = mapOf("if" to listOf(mapOf(">" to listOf(2, 1)), "apple", "banana")),
                    resultValue = JsonLogicResult.Success("apple")
                ),
                TestInput(
                    expression = mapOf("if" to listOf(mapOf(">" to listOf(1, 2)), "apple", "banana")),
                    resultValue = JsonLogicResult.Success("banana")
                ),
                TestInput(
                    expression = mapOf("if" to listOf(true)),
                    resultValue = JsonLogicResult.Success(true)
                ),
                TestInput(
                    expression = mapOf("if" to listOf(false)),
                    resultValue = JsonLogicResult.Success(false)
                ),
                TestInput(
                    expression = mapOf("if" to listOf("apple")),
                    resultValue = JsonLogicResult.Success("apple")
                ),
                TestInput(
                    expression = mapOf("if" to listOf(true, "apple")),
                    resultValue = JsonLogicResult.Success("apple")
                ),
                TestInput(
                    expression = mapOf("if" to listOf(emptyList<Any>(), "apple", "banana")),
                    resultValue = JsonLogicResult.Success("banana")
                ),
                TestInput(
                    expression = mapOf("if" to listOf(listOf(1), "apple", "banana")),
                    resultValue = JsonLogicResult.Success("apple")
                ),
                TestInput(
                    expression = mapOf("if" to listOf(listOf(1, 2, 3, 4), "apple", "banana")),
                    resultValue = JsonLogicResult.Success("apple")
                ),
                TestInput(
                    expression = mapOf("if" to listOf("", "apple", "banana")),
                    resultValue = JsonLogicResult.Success("banana")
                ),
                TestInput(
                    expression = mapOf("if" to listOf("zucchini", "apple", "banana")),
                    resultValue = JsonLogicResult.Success("apple")
                ),
                TestInput(
                    expression = mapOf("if" to listOf("0", "apple", "banana")),
                    resultValue = JsonLogicResult.Success("apple")
                ),
                TestInput(
                    expression = mapOf("if" to listOf(mapOf("+" to "0"), "apple", "banana")),
                    resultValue = JsonLogicResult.Success("banana")
                ),
                TestInput(
                    expression = mapOf("if" to listOf(mapOf("+" to "1"), "apple", "banana")),
                    resultValue = JsonLogicResult.Success("apple")
                ),
                TestInput(
                    expression = mapOf("if" to listOf(0, "apple", "banana")),
                    resultValue = JsonLogicResult.Success("banana")
                ),
                TestInput(
                    expression = mapOf("if" to listOf(1, "apple", "banana")),
                    resultValue = JsonLogicResult.Success("apple")
                ),
                TestInput(
                    expression = mapOf("if" to listOf(3.1416, "apple", "banana")),
                    resultValue = JsonLogicResult.Success("apple")
                ),
                TestInput(
                    expression = mapOf("if" to listOf(-1, "apple", "banana")),
                    resultValue = JsonLogicResult.Success("apple")
                ),
                TestInput(
                    expression = mapOf("if" to listOf(true, "yes", "no")),
                    resultValue = JsonLogicResult.Success("yes")
                ),
                TestInput(
                    expression = mapOf("if" to listOf(false, "yes", "no")),
                    resultValue = JsonLogicResult.Success("no")
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
                    resultValue = JsonLogicResult.Success("liquid")
                ),
                TestInput(
                    expression = mapOf("if" to listOf(false, "apple")),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(expression = mapOf("if" to listOf<Any>()), resultValue = JsonLogicResult.NullResultFailure),
                TestInput(
                    expression = mapOf("if" to listOf(false, "apple", false, "banana")),
                    resultValue = JsonLogicResult.NullResultFailure
                ),
                TestInput(
                    expression = mapOf("if" to listOf(false, "apple", false, "banana", false, "carrot")),
                    resultValue = JsonLogicResult.NullResultFailure
                )
            )
        )
    }
})
