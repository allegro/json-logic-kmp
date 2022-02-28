package operations.logic.unwrap

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class TruthyUnwrapStrategyTest : BehaviorSpec({
    val strategyImplementation: TruthyUnwrapStrategy = object : TruthyUnwrapStrategy {}

    given("A string zero") {
        val wrappedValue = "0"
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsBoolean(wrappedValue)
            then("should be true") {
                unwrapResult shouldBe true
            }
        }
    }

    given("A null") {
        val wrappedValue = null
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsBoolean(wrappedValue)
            then("should be false") {
                unwrapResult shouldBe false
            }
        }
    }

    given("Any non-empty string") {
        val wrappedValue = "anything"
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsBoolean(wrappedValue)
            then("should be true") {
                unwrapResult shouldBe true
            }
        }
    }

    given("An empty string") {
        val wrappedValue = ""
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsBoolean(wrappedValue)
            then("should be false") {
                unwrapResult shouldBe false
            }
        }
    }

    given("A blank string") {
        val wrappedValue = "     "
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsBoolean(wrappedValue)
            then("should be true") {
                unwrapResult shouldBe true
            }
        }
    }

    given("A non-empty list") {
        val wrappedValue = listOf("banana", "strawberry")
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsBoolean(wrappedValue)
            then("should be true") {
                unwrapResult shouldBe true
            }
        }
    }

    given("An empty list") {
        val wrappedValue = emptyList<Any>()
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsBoolean(wrappedValue)
            then("should be false") {
                unwrapResult shouldBe false
            }
        }
    }

    given("Any non-zero number") {
        val wrappedValue = -1
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsBoolean(wrappedValue)
            then("should be true") {
                unwrapResult shouldBe true
            }
        }
    }

    given("A zero number") {
        val wrappedValue = 0
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsBoolean(wrappedValue)
            then("should be false") {
                unwrapResult shouldBe false
            }
        }
    }
})
