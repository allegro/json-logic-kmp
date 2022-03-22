import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import operations.FunctionalLogicOperation
import operations.StandardLogicOperation

class JsonLogicEngineTest : BehaviorSpec({

    given("A functional operation") {
        val newFunctionalOperation: FunctionalLogicOperation = { _, _, _ -> "new operation result" }
        val logicEngine = JsonLogicEngine.Builder().addFunctionalOperation("newOne", newFunctionalOperation).build()
        val expression = mapOf("newOne" to listOf("argument"))

        `when`("added to the operations set") {
            val result = logicEngine.evaluate(expression, null)

            then("is available in the built engine") {
                result.shouldBeTypeOf<JsonLogicResult.Success>()
                result.value shouldBe "new operation result"
            }
        }
    }

    given("A standard operation") {
        val newStandardOperation: FunctionalLogicOperation = { _, _, _ -> "new operation result" }
        val logicEngine = JsonLogicEngine.Builder().addFunctionalOperation("newOne", newStandardOperation).build()
        val expression = mapOf("newOne" to listOf("argument"))

        `when`("added to the operations set") {
            val result = logicEngine.evaluate(expression, null)

            then("is available in the built engine") {
                result.shouldBeTypeOf<JsonLogicResult.Success>()
                result.value shouldBe "new operation result"
            }
        }
    }

    given("An overriding log operation") {
        val overridingOperation: StandardLogicOperation = { _, _ -> "new operation result" }
        val logicEngine = JsonLogicEngine.Builder().addStandardOperation("log", overridingOperation).build()
        val expression = mapOf("log" to listOf("argument"))

        `when`("added to the operations set") {
            val result = logicEngine.evaluate(expression, null)

            then("cannot override common implementation of it") {
                result.shouldBeTypeOf<JsonLogicResult.Success>()
                result.value shouldBe "argument"
            }
        }
    }

    given("An overriding functional operation") {
        val overridingOperation: FunctionalLogicOperation = { _, _, _ -> "new operation result" }
        val logicEngine = JsonLogicEngine.Builder().addFunctionalOperation("filter", overridingOperation).build()
        val expression = mapOf(
            "filter" to listOf(
                listOf(1, 2, 3, 4, 5),
                mapOf(">=" to listOf(mapOf("var" to ""), 2))
            )
        )

        `when`("added to the functional operations set") {
            val result = logicEngine.evaluate(expression, null)

            then("cannot override a common implementation of it") {
                result.shouldBeTypeOf<JsonLogicResult.Success>()
                result.value shouldBe listOf(2, 3, 4, 5)
            }
        }
    }

    given("An overriding functional operation") {
        val overridingOperation: StandardLogicOperation = { _, _ -> "new operation result" }
        val logicEngine = JsonLogicEngine.Builder().addStandardOperation("filter", overridingOperation).build()
        val expression = mapOf(
            "filter" to listOf(
                listOf(1, 2, 3, 4, 5),
                mapOf(">=" to listOf(mapOf("var" to ""), 2))
            )
        )

        `when`("added to the standard operations set") {
            val result = logicEngine.evaluate(expression, null)

            then("cannot override a common implementation of it") {
                result.shouldBeTypeOf<JsonLogicResult.Success>()
                result.value shouldBe listOf(2, 3, 4, 5)
            }
        }
    }

    given("An overriding standard operation") {
        val overridingOperation: StandardLogicOperation = { _, _ -> "new operation result" }
        val logicEngine = JsonLogicEngine.Builder().addStandardOperation("var", overridingOperation).build()
        val expression = mapOf("var" to listOf("argument"))
        val data = mapOf("argument" to "common operation result")

        `when`("added to the standard operations set") {
            val result = logicEngine.evaluate(expression, data)

            then("cannot override a common implementation of it") {
                result.shouldBeTypeOf<JsonLogicResult.Success>()
                result.value shouldBe "common operation result"
            }
        }
    }

    given("An overriding standard operation") {
        val overridingOperation: FunctionalLogicOperation = { _, _, _ -> "new operation result" }
        val logicEngine = JsonLogicEngine.Builder().addFunctionalOperation("var", overridingOperation).build()
        val expression = mapOf("var" to listOf("argument"))
        val data = mapOf("argument" to "common operation result")

        `when`("added to the functional operations set") {
            val result = logicEngine.evaluate(expression, data)

            then("cannot override a common implementation of it") {
                result.shouldBeTypeOf<JsonLogicResult.Success>()
                result.value shouldBe "common operation result"
            }
        }
    }
})
