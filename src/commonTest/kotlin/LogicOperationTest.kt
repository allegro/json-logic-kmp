import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf

abstract class LogicOperationTest(
    testName: String,
    successResultTestInput: List<TestInput.Successful> = emptyList(),
    failureResultTestInput: List<TestInput.Unsuccessful> = emptyList(),
    nameFunction: (TestInput) -> String = { it.resolveTestName() }
) : FunSpec({
    context("$testName - success results") {
        withData(
            nameFn = nameFunction,
            // given
            ts = successResultTestInput
        ) { (expression, data, result) ->
            // when
            val evaluationResult = JsonLogicEngine.instance.evaluate(expression, data)

            // then
            evaluationResult.shouldBeTypeOf<JsonLogicResult.Success>()
            evaluationResult.value shouldBe result
        }
    }

    context("$testName - failure results") {
        withData(
            nameFn = nameFunction,
            // given
            ts = failureResultTestInput
        ) { (expression, data) ->
            // when
            val evaluationResult = JsonLogicEngine.instance.evaluate(expression, data)

            // then
            evaluationResult.shouldBeTypeOf<JsonLogicResult.Failure>()
        }
    }
})

private fun TestInput.resolveTestName() =
    "Should apply $data on ${expression.keys} result in ${resolveResultValueInTestName()}"

private fun TestInput.resolveResultValueInTestName() = when (this) {
    is TestInput.Successful -> resultValue
    is TestInput.Unsuccessful -> "failure"
}
