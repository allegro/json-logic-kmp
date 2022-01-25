import expressions.Var
import kotlin.test.Test
import kotlin.test.assertEquals

class CommonJsonLogicEngineTest {

    @Test
    fun `should common engine always return SUCCESS`() {
        // given
        val logicEngine = CommonJsonLogicEngine()

        // when
        val result = logicEngine.evaluate("expression", mapOf())

        // then
        assertEquals(actual = result, expected = "SUCCESS")
    }

    @Test
    fun playground() {
        Var(parseStringLocal("{ \"var\" : [\"a\"] }"), parseStringLocal("{ \"a\" : 1, \"b\" : 2 }"))
//        Var("{ \"var\" : [\"a\"] }", "{ a : 1, b : 2 }").toString()
    }
}
