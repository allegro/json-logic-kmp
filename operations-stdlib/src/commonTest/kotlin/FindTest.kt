import kotlin.test.Test

class FindTest {
//    private val engine = JsonLogicEngine.Builder().addFunctionalOperation("find", Find).build()

    @Test
    private fun `al`() {
        val expression = mapOf("find" to listOf(listOf(-1, 1, 2, 3), mapOf(">" to listOf(mapOf("var" to ""), 0))))
//        engine.evaluate(expression, null)
    }
}
