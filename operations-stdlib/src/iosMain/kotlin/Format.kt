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

            runCatching { format.formatString(args) }
                .fold(
                    onSuccess = { it },
                    onFailure = { null }
                )
        }
    }

    private fun String.formatString(args: List<Any?>): String {
        val regEx = "%[\\d|.]*[sdf]|[%]".toRegex()
        val formats = regEx.getFormats(this)
        val rawStrings = this.split(regEx).toMutableList()

        if (args.isEmpty() || formats.isEmpty()) {
            return this
        }

        var formattedText = args.formatText(rawStrings, formats)

        // Add the rest of text if left
        formattedText += rawStrings.joinToString("")

        return formattedText
    }

    private fun Regex.getFormats(text: String) = findAll(text)
        .map {
            it.groupValues.first()
        }.toMutableList()

    private fun List<Any?>.formatText(rawStrings: MutableList<String>, formats: MutableList<String>): String {
        var formattedText = ""
        forEach {
            val singleFormat = formats.removeFirst()
            val rawString = rawStrings.removeFirst()
            val arg = it.toString()
            val formattedPart = when {
                singleFormat.contains("d") ->
                    NSString.stringWithFormat(rawString + singleFormat, arg.removeSuffix(".0").toInt())
                singleFormat.contains("f") ->
                    NSString.stringWithFormat(rawString + singleFormat, arg.toDouble())
                else -> NSString.stringWithFormat(rawString + singleFormat, arg.cstr)
            }
            formattedText += formattedPart
        }
        return formattedText
    }
}
