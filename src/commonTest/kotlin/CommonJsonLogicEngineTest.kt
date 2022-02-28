import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import utils.JsonLogicException

class CommonJsonLogicEngineTest : BehaviorSpec({
    val logicEngine = CommonJsonLogicEngine()

    given("An empty logic expression") {
        val logicExpression = emptyMap<String, Any?>()
        val data = listOf("apple", "banana")

        then("throws and exception on evaluation") {
            shouldThrow<JsonLogicException> {
                logicEngine.evaluate(logicExpression, data)
            }
        }
    }
})
