package encoding

import TestInput
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class EncodeTest: FunSpec({
    val operatorName = "encode"
    val logicEngine = JsonLogicEngine.Builder()
        .addStandardOperation(operatorName, Encode)
        .build()

    withData(
        nameFn = { input -> "Should evaluate encode operation into ${input.result}" },
        ts = listOf(
            TestInput(
                expression = mapOf(
                    operatorName to listOf("https://test.pl?id=1234&4321")
                ),
                result = JsonLogicResult.Success("https%3A%2F%2Ftest%2Epl%3Fid%3D1234%264321")
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("Foo&Bar")
                ),
                result = JsonLogicResult.Success("Foo%26Bar")
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("Foo#Bar")
                ),
                result = JsonLogicResult.Success("Foo%23Bar")
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("https://test.pl?name=%C5%BC%C3%B3%C5%82%C4%85d%C5%BA")
                ),
                result = JsonLogicResult.Success(
                    "https%3A%2F%2Ftest%2Epl%3Fname%3D%25C5%25BC%25C3%25B3%25C5%2582%25C4%2585d%25C5%25BA"
                )
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("zażółć gęślą jaźń")
                ),
                result = JsonLogicResult.Success(
                    "za%C5%BC%C3%B3%C5%82%C4%87%20g%C4%99%C5%9Bl%C4%85%20ja%C5%BA%C5%84"
                )
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("kalendář")
                ),
                result = JsonLogicResult.Success("kalend%C3%A1%C5%99")
            ),
            TestInput(
                expression = mapOf(
                    operatorName to listOf("Доброго ранку")
                ),
                result = JsonLogicResult.Success(
                    "%D0%94%D0%BE%D0%B1%D1%80%D0%BE%D0%B3%D0%BE%20%D1%80%D0%B0%D0%BD%D0%BA%D1%83"
                )
            )
        )
        // given
    ) { testInput: TestInput ->
        // when
        val evaluationResult = logicEngine.evaluate(testInput.expression, testInput.data)

        // then
        evaluationResult shouldBe testInput.result
    }
})
