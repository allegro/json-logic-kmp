import kotlinx.datetime.Clock

// TODO Discuss when implementing stdlib if we want to use this library for time.
object CurrentTimeMillis : StandardLogicOperation {
    override fun invoke(expression: Any?, data: Any?): Any = Clock.System.now().toEpochMilliseconds()
}
