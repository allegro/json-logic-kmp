import operation.StandardLogicOperation
import platform.Foundation.NSDate
import platform.Foundation.timeIntervalSince1970

actual object CurrentTimeMillis : StandardLogicOperation {
    private const val MILLIS_IN_SECOND: Int = 1_000

    actual override fun evaluateLogic(expression: Any?, data: Any?): Any {
        /*
            `timeIntervalSince1970()` return Double value in seconds with fraction part.
            We have to multiply this by 1000 to move fractions value
        */
        val currentTimestampInMillis = { NSDate().timeIntervalSince1970() * MILLIS_IN_SECOND }

        // We have to convert Double to Long to remove fraction part.
        return  -1//currentTimestampInMillis().toLong()
    }
}
