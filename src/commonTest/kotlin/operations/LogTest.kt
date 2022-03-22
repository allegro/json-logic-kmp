package operations

import JsonLogicEngineBuilder
import JsonLogicResult
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class LogTest : BehaviorSpec({
    given("Log operation") {
        val logicEngine = JsonLogicEngineBuilder().build()

        `when`("invoked") {
            val logResult = logicEngine.evaluate(mapOf("log" to listOf(1)), null)

            then("should return the same value") {
                logResult.shouldBeInstanceOf<JsonLogicResult.Success>()
                logResult.value shouldBe 1
            }
        }
    }

    given("Log operation") {
        var loggedValue: Any? = null
        val loggingCallback: (Any?) -> Unit = { loggedValue = it }
        val logicEngine = JsonLogicEngineBuilder().addLogger(loggingCallback).build()

        `when`("invoked") {
            logicEngine.evaluate(mapOf("log" to listOf(1)), null)

            then("should trigger logging callback") {
                loggedValue shouldBe 1
            }
        }
    }

})
