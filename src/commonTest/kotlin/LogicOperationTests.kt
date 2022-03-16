import io.kotest.core.spec.style.scopes.ContainerScope
import io.kotest.core.spec.style.scopes.FunSpecContainerScope
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf

suspend fun FunSpecContainerScope.testWithSuccessResultData(
    data: List<TestInput.Successful>,
    nameFunction: (TestInput.Successful) -> String = { "Should apply ${it.data} on ${it.expression} result in ${it.resultValue}" }
) = withData(
    nameFn = nameFunction,
    // given
    ts = data,
    test = successResultTestBody()
)

private fun successResultTestBody(): ContainerScope.(TestInput.Successful) -> Unit = { (expression, data, result) ->
    // when
    val evaluationResult = JsonLogicEngine.instance.evaluate(expression, data)

    // then
    evaluationResult.shouldBeTypeOf<JsonLogicResult.Success>()
    evaluationResult.value shouldBe result
}

suspend fun FunSpecContainerScope.testWithFailureResultData(
    data: List<TestInput.Unsuccessful>,
    nameFunction: (TestInput.Unsuccessful) -> String = { "Should apply ${it.data} on ${it.expression} result in failure" }
) = withData(
    nameFn = nameFunction,
    // given
    ts = data,
    test = failureResultTestBody()
)

private fun failureResultTestBody(): ContainerScope.(TestInput.Unsuccessful) -> Unit = { (expression, data) ->
    // when
    val evaluationResult = JsonLogicEngine.instance.evaluate(expression, data)

    // then
    evaluationResult.shouldBeTypeOf<JsonLogicResult.Failure>()
}
