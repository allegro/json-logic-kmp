import operation.StandardLogicOperation
import platform.Foundation.NSString
import platform.Foundation.stringWithFormat
import utils.asList
import utils.secondOrNull

actual object Format : StandardLogicOperation {
    actual override fun evaluateLogic(expression: Any?, data: Any?): Any? {
        return with(expression.asList) {
            val format = firstOrNull().toString()
            val args = arrayOf(secondOrNull())
            return runCatching { format.formatFun(args) }
                .fold( { it }, { null } )
        }
    }

    private fun String.formatFun(vararg args: Any?) =
        // https://stackoverflow.com/questions/62113545/how-to-pass-vararg-parameters-from-kotlin-common-code-to-ios-functions
        // This work around is because varargs can't be passed to Objective-C
        when (args.size) {
            0 -> NSString.stringWithFormat(this)
            1 -> NSString.stringWithFormat(this, args[0])
            2 -> NSString.stringWithFormat(this, args[0], args[1])
            3 -> NSString.stringWithFormat(this, args[0], args[1], args[2])
            4 -> NSString.stringWithFormat(this, args[0], args[1], args[2], args[3])
            5 -> NSString.stringWithFormat(this, args[0], args[1], args[2], args[3], args[4])
            6 -> NSString.stringWithFormat(this, args[0], args[1], args[2], args[3], args[4], args[5])
            7 -> NSString.stringWithFormat(this, args[0], args[1], args[2], args[3], args[4], args[5], args[6])
            8 -> NSString.stringWithFormat(this, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7])
            9 -> NSString.stringWithFormat(this, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8])
            10 -> NSString.stringWithFormat(this, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9])
            11 -> NSString.stringWithFormat(this, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9], args[10])
            12 -> NSString.stringWithFormat(this, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9], args[10], args[11])
            13 -> NSString.stringWithFormat(this, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9], args[10], args[11], args[12])
            14 -> NSString.stringWithFormat(this, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9], args[10], args[11], args[12], args[13])
            15 -> NSString.stringWithFormat(this, args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9], args[10], args[11], args[12], args[13], args[14])
            else -> throw IllegalStateException("ios String.format() can only accept up to 15 arguments")
        }
}
