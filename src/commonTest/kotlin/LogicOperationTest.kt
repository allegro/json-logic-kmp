import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

open class LogicOperationTest(testName: String, testInput: List<TestInput>, nameFunction: (TestInput) -> String) :
    FunSpec({
        context(testName) {
            withData(
                nameFn = nameFunction,
                // given
                ts = testInput
            ) { (expression, data, result) ->
                // when
                val evaluationResult = JsonLogicEngine.instance.evaluate(expression, data)

                // then
                evaluationResult shouldBe result
            }
        }
    })
