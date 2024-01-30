package string

import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import JsonLogicResult.Success
import JsonLogicResult.Failure

class SplitTest : FunSpec({
    val operatorName = "split"
    val logicEngine = JsonLogicEngine.Builder().addStandardOperation(operatorName, Split).build()

    withData(
        nameFn = { input -> "Should evaluated ${input.expression} with given ${input.data} result in ${input.result}" },
        ts = listOf(
            TestInput(
                expression = mapOf(operatorName to listOf("one,two,three", listOf(","))),
                result = Success(listOf("one", "two", "three"))
            ),
            TestInput(
                expression = mapOf(operatorName to listOf("one, two, three", listOf(","))),
                result = Success(listOf("one", " two", " three"))
            ),
            TestInput(
                expression = mapOf(operatorName to listOf("one, two, three", listOf(", "))),
                result = Success(listOf("one", "two", "three"))
            ),
            TestInput(
                expression = mapOf(operatorName to listOf("/location/to/file", listOf("/"))),
                result = Success(listOf("", "location", "to", "file"))
            ),
            TestInput(
                expression = mapOf(operatorName to listOf("location\\to\\file", listOf("\\"))),
                result = Success(listOf("location", "to", "file"))
            ),
            TestInput(
                expression = mapOf(operatorName to listOf("oneAtwoAthree", listOf("A"))),
                result = Success(listOf("one", "two", "three"))
            ),
            TestInput(
                expression = mapOf(operatorName to listOf("The quick brown fox jumps over the lazy dog", listOf(" "))),
                result = Success(listOf("The", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog"))
            ),
            TestInput(
                expression = mapOf(operatorName to listOf("https://domain.net/query/search", listOf("/"))),
                result = Success(listOf("https:", "", "domain.net", "query", "search"))
            ),
            TestInput(
                expression = mapOf(operatorName to listOf("Hello", listOf(""))),
                result = Success(listOf("", "H", "e", "l", "l", "o", ""))
            ),
            TestInput(
                expression = mapOf(operatorName to listOf("", listOf(""))),
                result = Success(listOf("", ""))
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(null, null)),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to listOf(null, ",")),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to listOf("Hello", "")),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to listOf("Hello", 1)),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to listOf("Hello", true)),
                result = Failure.NullResult
            ),
            TestInput(
                expression = mapOf(operatorName to listOf("Hello", listOf(0, 1))),
                result = Failure.MissingOperation
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
