import expressions.Var
import kotlin.test.Test
import kotlin.test.assertEquals

class CommonJsonLogicEngineTest {
    @Test
    fun playground() {
        CommonJsonLogicEngine().evaluate(mapOf("var" to listOf("a")), mapOf("a" to 1.0, "b" to 2.0))
        Var(parseStringLocal("{ \"var\" : [\"a\"] }"), parseStringLocal("{ \"a\" : 1, \"b\" : 2 }"))
        Var("{ \"var\" : [\"a\"] }", "{ a : 1, b : 2 }").toString()
    }
}
