package string

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class StringUnwrapStrategyTest : BehaviorSpec({
    val strategyImplementation: StringUnwrapStrategy = object : StringUnwrapStrategy {}

    given("A list with one string") {
        val wrappedValue = listOf("test")
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsString(wrappedValue)
            then("should be string") {
                unwrapResult shouldBe "test"
            }
        }
    }

    given("A string") {
        val wrappedValue = "apple"
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsString(wrappedValue)
            then("should be string") {
                unwrapResult shouldBe "apple"
            }
        }
    }

    given("A list of strings") {
        val wrappedValue = listOf("apple", "banana")
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsString(wrappedValue)
            then("should not be unwrapped") {
                unwrapResult shouldBe null
            }
        }
    }

    given("A non-decimal double") {
        val wrappedValue = listOf(2.0)
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsString(wrappedValue)
            then("should not be unwrapped") {
                unwrapResult shouldBe null
            }
        }
    }

    given("A double") {
        val wrappedValue = listOf(2.51)
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsString(wrappedValue)
            then("should not be unwrapped") {
                unwrapResult shouldBe null
            }
        }
    }
    given("An empty list") {
        val wrappedValue = emptyList<String>()
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsString(wrappedValue)
            then("should not be unwrapped") {
                unwrapResult shouldBe null
            }
        }
    }

    given("A boolean value") {
        val wrappedValue = listOf(false)
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsString(wrappedValue)
            then("should not be unwrapped") {
                unwrapResult shouldBe null
            }
        }
    }

    given("A null") {
        val wrappedValue = listOf(null)
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsString(wrappedValue)
            then("should not be unwrapped") {
                unwrapResult shouldBe null
            }
        }
    }

    given("A nested list items") {
        val wrappedValue = listOf(listOf(listOf("banana", "apple", "pineapple")))
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsString(wrappedValue)
            then("should not be unwrapped") {
                unwrapResult shouldBe null
            }
        }
    }

    given("A nested lists items") {
        val wrappedValue = listOf(true, listOf(listOf("banana")), listOf(2.0))
        `when`("unwrapped") {
            val unwrapResult = strategyImplementation.unwrapValueAsString(wrappedValue)
            then("should not be unwrapped") {
                unwrapResult shouldBe null
            }
        }
    }
})
