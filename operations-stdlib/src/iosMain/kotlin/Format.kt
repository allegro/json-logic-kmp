import kotlinx.cinterop.cstr
import operation.StandardLogicOperation
import platform.Foundation.NSString
import platform.Foundation.stringWithFormat
import utils.asList
import utils.secondOrNull

actual object Format : StandardLogicOperation {
    actual override fun evaluateLogic(expression: Any?, data: Any?): Any? {
        return with(expression.asList) {
            val format = firstOrNull().toString()
            val args = secondOrNull().asList

            return runCatching { format.formatFun(args) }
                .fold( { it }, { null } )
        }
    }

    private fun String.formatFun(args: List<Any?>): String? {
        var formattedText = ""
        val regEx = "%[\\d|.]*[sdf]|[%]".toRegex()
        val singleFormats: MutableList<String> = regEx.findAll(this).map {
            it.groupValues.first()
        }.asSequence().toMutableList()
        val rawStrings = this.split(regEx).toMutableList()

        if (args.isEmpty() || singleFormats.isEmpty()) { return this }

        for (i in 0 until args.count()) {
            val singleFormat = singleFormats.removeFirst()
            val rawString = rawStrings.removeFirst()
            var arg = args[i].toString()
            val formattedPart = if (singleFormat.contains("d")) {
                NSString.stringWithFormat(rawString + singleFormat, arg.removeSuffix(".0").toInt())
            } else if(singleFormat.contains("f")) {
                NSString.stringWithFormat(rawString + singleFormat, arg.toDouble())
            } else {
                NSString.stringWithFormat(rawString + singleFormat, arg.cstr)
            }
            formattedText += formattedPart
        }
        // Add the rest of text if left
        formattedText += rawStrings.joinToString("")

        return  formattedText
    }

    private fun String.isInt() = toIntOrNull() != null
    private fun String.isDouble() = toDoubleOrNull() != null
}
