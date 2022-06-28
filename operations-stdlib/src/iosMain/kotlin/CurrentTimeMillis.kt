import operation.StandardLogicOperation
import platform.Foundation.NSDate
import platform.Foundation.timeIntervalSince1970

actual object CurrentTimeMillis: StandardLogicOperation {
    actual override fun evaluateLogic(expression: Any?, data: Any?): Any {
        val currentTimestamp = NSDate().timeIntervalSince1970()

        /*
            `timeIntervalSince1970()` return Double value with fraction part in seconds.
            We have to multiply this by 1000 to move fractions value
        */
        val currentTimestampInMillis = currentTimestamp * 1_000

        // We have to convert Double to Long to remove fraction part.
        return  currentTimestampInMillis.toLong()
    }
}
