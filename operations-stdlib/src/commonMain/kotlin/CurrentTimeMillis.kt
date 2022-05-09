import kotlinx.datetime.Clock
import operation.StandardLogicOperation

// TODO Discuss when implementing stdlib if we want to use this library for time.
object CurrentTimeMillis : StandardLogicOperation {
    override fun evaluateLogic(expression: Any?, data: Any?): Any = Clock.System.now().toEpochMilliseconds()
}
