package operations

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BooleanUnwrapStrategyTest : BehaviorSpec({
    val strategyImplementation: BooleanUnwrapStrategy = object : BooleanUnwrapStrategy {}

    given("A boolean") {
        val wrappedValue = true
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsBoolean(wrappedValue)
            then("should has the same value") {
                unwrapResult shouldBe true
            }
        }
    }

    given("A positive number") {
        val wrappedValue = 2.5
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsBoolean(wrappedValue)
            then("should be true") {
                unwrapResult shouldBe true
            }
        }
    }

    given("A positive number") {
        val wrappedValue = 0.5
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsBoolean(wrappedValue)
            then("should has its decimal value ignored and be false") {
                unwrapResult shouldBe false
            }
        }
    }

    given("A positive number string") {
        val wrappedValue = "0.5"
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsBoolean(wrappedValue)
            then("should has its decimal value ignored and be false") {
                unwrapResult shouldBe false
            }
        }
    }

    given("A positive number string") {
        val wrappedValue = "2.5"
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsBoolean(wrappedValue)
            then("should be true") {
                unwrapResult shouldBe true
            }
        }
    }

    given("A zero") {
        val wrappedValue = 0
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsBoolean(wrappedValue)
            then("should be false") {
                unwrapResult shouldBe false
            }
        }
    }

    given("A negative number") {
        val wrappedValue = -2.5
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsBoolean(wrappedValue)
            then("should be false") {
                unwrapResult shouldBe false
            }
        }
    }

    given("A negative number") {
        val wrappedValue = -0.5
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsBoolean(wrappedValue)
            then("should has its decimal value ignored and be false") {
                unwrapResult shouldBe false
            }
        }
    }

    given("A negative number string") {
        val wrappedValue = "-0.5"
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsBoolean(wrappedValue)
            then("should has its decimal value ignored and be false") {
                unwrapResult shouldBe false
            }
        }
    }

    given("Any string value") {
        val wrappedValue = "banana"
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsBoolean(wrappedValue)
            then("should by null") {
                unwrapResult shouldBe null
            }
        }
    }

    given("Any other type value") {
        val wrappedValue = Pair("banana", 1)
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsBoolean(wrappedValue)
            then("should by null") {
                unwrapResult shouldBe null
            }
        }
    }
})
