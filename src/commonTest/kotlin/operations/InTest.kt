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
            data = emptyMap<String, Any>(),
            resultValue = true
        ),
        TestInput(
            expression = mapOf("in" to listOf("Milhouse", listOf("Bart", "Homer", "Lisa", "Marge", "Maggie"))),
            data = emptyMap<String, Any>(),
            resultValue = false
        ),
        TestInput(
            expression = mapOf("in" to listOf("Spring", "Springfield")),
            data = emptyMap<String, Any>(),
            resultValue = true
        ),
        TestInput(
            expression = mapOf("in" to listOf("i", "team")),
            data = emptyMap<String, Any>(),
            resultValue = false
        ),
        TestInput(
            expression = mapOf("in" to listOf("t", listOf("team"))),
            data = emptyMap<String, Any>(),
            resultValue = false
        ),
        TestInput(
            expression = mapOf("in" to listOf("1", 133)),
            data = emptyMap<String, Any>(),
            resultValue = false
        ),
        TestInput(
            expression = mapOf("in" to listOf(1, "133")),
            data = emptyMap<String, Any>(),
            resultValue = true
        ),
        TestInput(
            expression = mapOf("in" to listOf(1, 133)),
            data = emptyMap<String, Any>(),
            resultValue = false
        ),
        TestInput(
            expression = mapOf("in" to listOf("t", true)),
            data = emptyMap<String, Any>(),
            resultValue = false
        ),
        TestInput(
            expression = mapOf("in" to listOf(true, "true gold")),
            data = emptyMap<String, Any>(),
            resultValue = true
        ),
        TestInput(
            expression = mapOf("in" to listOf(null, "banana")),
            data = emptyMap<String, Any>(),
            resultValue = false
        ),
        TestInput(
            expression = mapOf("in" to listOf("n", null)),
            data = emptyMap<String, Any>(),
            resultValue = false
        ),
        TestInput(
            expression = mapOf("in" to listOf(null, listOf(null, null))),
            data = emptyMap<String, Any>(),
            resultValue = true
        ),
        TestInput(
            expression = mapOf("in" to listOf(null, listOf(listOf(null), "banana"))),
            data = emptyMap<String, Any>(),
            resultValue = false
        ),
        TestInput(
            expression = mapOf("in" to listOf(null, listOf("", ""))),
            data = emptyMap<String, Any>(),
            resultValue = false
        ),
        TestInput(
            expression = mapOf("in" to listOf("t", "true")),
            data = emptyMap<String, Any>(),
            resultValue = true
        ),
        TestInput(
            expression = mapOf("in" to listOf("j", "apple", "juice")),
            data = emptyMap<String, Any>(),
            resultValue = false
        ),
        TestInput(
            expression = mapOf("in" to listOf("Spring", "Springfield")),
            data = emptyMap<String, Any>(),
            resultValue = true
        ),
    )
)
