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
}
