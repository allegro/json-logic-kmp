package operations

import LogicOperationTest
import TestInput

class InTest : LogicOperationTest(
    testName = "JsonLogic evaluation with only In operation",
    successResultTestInput = listOf(
        TestInput(
            expression = mapOf(
                "in" to listOf(
                    mapOf("var" to "filling"),
                    listOf("apple", "cherry")
                )
            ),
            data = mapOf("filling" to "apple"),
            resultValue = true
        ),
        TestInput(
            expression = mapOf("in" to listOf("Bart", listOf("Bart", "Homer", "Lisa", "Marge", "Maggie"))),
            resultValue = true
        ),
        TestInput(
            expression = mapOf("in" to listOf("Milhouse", listOf("Bart", "Homer", "Lisa", "Marge", "Maggie"))),
            resultValue = false
        ),
        TestInput(
            expression = mapOf("in" to listOf("Spring", "Springfield")),
            resultValue = true
        ),
        TestInput(
            expression = mapOf("in" to listOf("i", "team")),
            resultValue = false
        ),
        TestInput(
            expression = mapOf("in" to listOf("t", listOf("team"))),
            resultValue = false
        ),
        TestInput(
            expression = mapOf("in" to listOf("1", 133)),
            resultValue = false
        ),
        TestInput(
            expression = mapOf("in" to listOf(1, "133")),
            resultValue = true
        ),
        TestInput(
            expression = mapOf("in" to listOf(1, 133)),
            resultValue = false
        ),
        TestInput(
            expression = mapOf("in" to listOf("t", true)),
            resultValue = false
        ),
        TestInput(
            expression = mapOf("in" to listOf(true, "true gold")),
            resultValue = true
        ),
        TestInput(
            expression = mapOf("in" to listOf(null, "banana")),
            resultValue = false
        ),
        TestInput(
            expression = mapOf("in" to listOf("n", null)),
            resultValue = false
        ),
        TestInput(
            expression = mapOf("in" to listOf(null, listOf(null, null))),
            resultValue = true
        ),
        TestInput(
            expression = mapOf("in" to listOf(null, listOf(listOf(null), "banana"))),
            resultValue = false
        ),
        TestInput(
            expression = mapOf("in" to listOf(null, listOf("", ""))),
            resultValue = false
        ),
        TestInput(
            expression = mapOf("in" to listOf("t", "true")),
            resultValue = true
        ),
        TestInput(
            expression = mapOf("in" to listOf("j", "apple", "juice")),
            resultValue = false
        ),
        TestInput(
            expression = mapOf("in" to listOf("Spring", "Springfield")),
            resultValue = true
        ),
    )
)
