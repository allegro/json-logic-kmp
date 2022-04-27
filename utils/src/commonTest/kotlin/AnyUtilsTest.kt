import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import utils.isExpression

class AnyUtilsTest : BehaviorSpec({
    given("A list") {
        val wrappedValue = listOf("banana", 1)
        `when`("checked") {
            val unwrapResult = wrappedValue.isExpression()
            then("should not be treated as an expression") {
                unwrapResult shouldBe false
            }
        }
    }

    given("A single value") {
        val wrappedValue = "fake map"
        `when`("checked") {
            val unwrapResult = wrappedValue.isExpression()
            then("should not be treated as an expression") {
                unwrapResult shouldBe false
            }
        }
    }

    given("An empty map") {
        val wrappedValue = emptyMap<String, Any>()
        `when`("checked") {
            val unwrapResult = wrappedValue.isExpression()
            then("should not be treated as an expression") {
                unwrapResult shouldBe false
            }
        }
    }

    given("A map with keys other than strings") {
        val wrappedValue = mapOf(1 to "var")
        `when`("checked") {
            val unwrapResult = wrappedValue.isExpression()
            then("should not be treated as an expression") {
                unwrapResult shouldBe false
            }
        }
    }

    given("A map with string keys") {
        val wrappedValue = mapOf("var" to "a.b")
        `when`("checked") {
            val unwrapResult = wrappedValue.isExpression()
            then("should be treated as an expression") {
                unwrapResult shouldBe true
            }
        }
    }
})
