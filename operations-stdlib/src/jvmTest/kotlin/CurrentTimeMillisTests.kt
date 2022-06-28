import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.should
import io.kotest.matchers.types.beInstanceOf

class CurrentTimeMillisTests : FunSpec() {
    init {
        val operatorName = "currentTime"
        val logicEngine = JsonLogicEngine.Builder()
            .addStandardOperation(operatorName, CurrentTimeMillis)
            .build()

        test("CurrentTimeMillis.evaluateLogic should be Long type") {
            val result = logicEngine.evaluate(emptyMap(), null)
            result should beInstanceOf<Long>()
        }
    }
}
