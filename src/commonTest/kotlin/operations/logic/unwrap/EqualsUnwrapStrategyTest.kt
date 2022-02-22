package operations.logic.unwrap

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class EqualsUnwrapStrategyTest : BehaviorSpec({
    val strategyImplementation: EqualsUnwrapStrategy = object : EqualsUnwrapStrategy {}

    given("A number") {
        val wrappedValue = 20
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValue(wrappedValue)
            then("should has the same decimal value") {
                unwrapResult shouldBe 20.0
            }
        }
    }

    given("A number string") {
        val wrappedValue = "12"
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValue(wrappedValue)
            then("should has the decimal value") {
                unwrapResult shouldBe 12.0
            }
        }
    }

    given("Any string") {
        val wrappedValue = "banana"
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValue(wrappedValue)
            then("should has the same value") {
                unwrapResult shouldBe "banana"
            }
        }
    }

    given("A list with single null") {
        val wrappedValue = listOf(null)
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValue(wrappedValue)
            then("should be 0") {
                unwrapResult shouldBe 0.0
            }
        }
    }

    given("A list with single boolean") {
        val wrappedValue = listOf(true)
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValue(wrappedValue)
            then("should be the same value") {
                unwrapResult shouldBe listOf(true)
            }
        }
    }

    given("A list with single value") {
        val wrappedValue = listOf(20)
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValue(wrappedValue)
            then("should be flattened value") {
                unwrapResult shouldBe 20.0
            }
        }
    }

    given("A list with multiple values") {
        val wrappedValue = listOf(2, "banana")
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValue(wrappedValue)
            then("should be the same") {
                unwrapResult shouldBe listOf(2, "banana")
            }
        }
    }

    given("An empty list") {
        val wrappedValue = emptyList<String>()
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValue(wrappedValue)
            then("should be an empty string") {
                unwrapResult shouldBe ""
            }
        }
    }

    given("A nested empty list") {
        val wrappedValue = listOf(emptyList<String>())
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValue(wrappedValue)
            then("should be an empty string") {
                unwrapResult shouldBe ""
            }
        }
    }

    given("A true value") {
        val wrappedValue = true
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValue(wrappedValue)
            then("should be 1") {
                unwrapResult shouldBe 1.0
            }
        }
    }

    given("A false value") {
        val wrappedValue = false
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValue(wrappedValue)
            then("should be 0") {
                unwrapResult shouldBe 0.0
            }
        }
    }

    given("Any other type value") {
        val wrappedValue = Pair(1, 2)
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValue(wrappedValue)
            then("should be the same") {
                unwrapResult shouldBe Pair(1, 2)
            }
        }
    }
})
