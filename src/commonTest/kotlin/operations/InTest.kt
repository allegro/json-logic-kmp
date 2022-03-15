package operations

import LogicOperationTest
import TestInput.Successful

class InTest : LogicOperationTest(
    testName = "JsonLogic evaluation with only In operation",
    successResultTestInput = listOf(
        Successful(
            expression = mapOf(
                "in" to listOf(
                    mapOf("var" to "filling"),
                    listOf("apple", "cherry")
                )
            ),
            data = mapOf("filling" to "apple"),
            resultValue = true
        ),
        Successful(
            expression = mapOf("in" to listOf("Bart", listOf("Bart", "Homer", "Lisa", "Marge", "Maggie"))),
            resultValue = true
        ),
        Successful(
            expression = mapOf("in" to listOf("Milhouse", listOf("Bart", "Homer", "Lisa", "Marge", "Maggie"))),
            resultValue = false
        ),
        Successful(
            expression = mapOf("in" to listOf("Spring", "Springfield")),
            resultValue = true
        ),
        Successful(
            expression = mapOf("in" to listOf("i", "team")),
            resultValue = false
        ),
        Successful(
            expression = mapOf("in" to listOf("t", listOf("team"))),
            resultValue = false
        ),
        Successful(
            expression = mapOf("in" to listOf("1", 133)),
            resultValue = false
        ),
        Successful(
            expression = mapOf("in" to listOf(1, "133")),
            resultValue = true
        ),
        Successful(
            expression = mapOf("in" to listOf(1, 133)),
            resultValue = false
        ),
        Successful(
            expression = mapOf("in" to listOf("t", true)),
            resultValue = false
        ),
        Successful(
            expression = mapOf("in" to listOf(true, "true gold")),
            resultValue = true
        ),
        Successful(
            expression = mapOf("in" to listOf(null, "banana")),
            resultValue = false
        ),
        Successful(
            expression = mapOf("in" to listOf("n", null)),
            resultValue = false
        ),
        Successful(
            expression = mapOf("in" to listOf(null, listOf(null, null))),
            resultValue = true
        ),
        Successful(
            expression = mapOf("in" to listOf(null, listOf(listOf(null), "banana"))),
            resultValue = false
        ),
        Successful(
            expression = mapOf("in" to listOf(null, listOf("", ""))),
            resultValue = false
        ),
        Successful(
            expression = mapOf("in" to listOf("t", "true")),
            resultValue = true
        ),
        Successful(
            expression = mapOf("in" to listOf("j", "apple", "juice")),
            resultValue = false
        ),
        Successful(
            expression = mapOf("in" to listOf("Spring", "Springfield")),
            resultValue = true
        ),
    )
)
