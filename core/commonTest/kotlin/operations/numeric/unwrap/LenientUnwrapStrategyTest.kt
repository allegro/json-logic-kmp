package operations.numeric.unwrap

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class LenientUnwrapStrategyTest : BehaviorSpec({
    val strategyImplementation: LenientUnwrapStrategy = object : LenientUnwrapStrategy {}

    given("A false value") {
        val wrappedValue = listOf(false)
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsDouble(wrappedValue)
            then("should be equal to 0") {
                unwrapResult shouldHaveSize 1
                unwrapResult.first() shouldBe 0.0
            }
        }
    }

    given("A true value") {
        val wrappedValue = listOf(true)
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsDouble(wrappedValue)
            then("should be equal to 1") {
                unwrapResult shouldHaveSize 1
                unwrapResult.first() shouldBe 1.0
            }
        }
    }

    given("A null") {
        val wrappedValue = listOf(null)
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsDouble(wrappedValue)
            then("should be equal to 0") {
                unwrapResult shouldHaveSize 1
                unwrapResult.first() shouldBe 0.0
            }
        }
    }

    given("Any other value") {
        val wrappedValue = listOf(Pair("a", "apple"))
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsDouble(wrappedValue)
            then("should return null") {
                unwrapResult shouldHaveSize 1
                unwrapResult.first() shouldBe null
            }
        }
    }

    given("A number string") {
        val wrappedValue = listOf("32.5")
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsDouble(wrappedValue)
            then("should be equal to its number value") {
                unwrapResult shouldHaveSize 1
                unwrapResult.first() shouldBe 32.5
            }
        }
    }

    given("A not number string") {
        val wrappedValue = listOf("Not a number")
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsDouble(wrappedValue)
            then("should be null") {
                unwrapResult shouldHaveSize 1
                unwrapResult.first() shouldBe null
            }
        }
    }

    given("An empty list") {
        val wrappedValue = listOf(emptyList<String>())
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsDouble(wrappedValue)
            then("should be equal to 0") {
                unwrapResult shouldHaveSize 1
                unwrapResult.first() shouldBe 0
            }
        }
    }

    given("A more than 2 element list") {
        val wrappedValue = listOf(listOf(1, 2, "Not a number"))
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsDouble(wrappedValue)
            then("should be null") {
                unwrapResult shouldHaveSize 1
                unwrapResult.first() shouldBe null
            }
        }
    }

    given("A single element list") {
        val wrappedValue = listOf(listOf("20"))
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsDouble(wrappedValue)
            then("should be equal to its element") {
                unwrapResult shouldHaveSize 1
                unwrapResult.first() shouldBe 20
            }
        }
    }

    given("A nested single element list") {
        val wrappedValue = listOf(listOf(listOf("20")))
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsDouble(wrappedValue)
            then("should be equal to its deepest element") {
                unwrapResult shouldHaveSize 1
                unwrapResult.first() shouldBe 20
            }
        }
    }
})
