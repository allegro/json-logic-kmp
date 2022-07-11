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
        if (args.isEmpty()) { return null }
        var formattedText = ""
        val regEx = "%[\\d|.]*[sdf]|[%]".toRegex()
        val singleFormats: List<String> = regEx.findAll(this).map {
            it.groupValues.first()
        }.asSequence().toList()
        val newStrings = this.split(regEx)
        for (i in 0 until args.count()) {
            val singleFormat = singleFormats[i]
            var arg = args[i].toString().removeSuffix(".0")
            val formattedPart = if (arg.isInt()) {
                NSString.stringWithFormat(newStrings[i] + singleFormat, arg.toInt())
            } else if(arg.isDouble()) {
                NSString.stringWithFormat(newStrings[i] + singleFormat, arg.toDouble())
            } else {
                NSString.stringWithFormat(newStrings[i] + "%@", arg.cstr)
            }
            formattedText += formattedPart
        }
        return  formattedText
    }

    private fun String.isInt() = toIntOrNull() != null
    private fun String.isDouble() = toDoubleOrNull() != null
}
