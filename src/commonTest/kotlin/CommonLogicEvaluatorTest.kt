import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import utils.JsonLogicException

class CommonLogicEvaluatorTest : BehaviorSpec({
    val evaluator = CommonLogicEvaluator(LogicOperations())

    given("An unknown operation") {
        val logicExpression = mapOf("+" to listOf(2, mapOf("unknown" to "3")))

        then("throws an exception on evaluation") {
            shouldThrow<JsonLogicException> {
                evaluator.evaluateLogic(logicExpression, null)
            }
        }
    }
})
