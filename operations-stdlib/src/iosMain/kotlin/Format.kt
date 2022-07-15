import kotlinx.cinterop.cstr
import operation.StandardLogicOperation
import platform.Foundation.NSString
import platform.Foundation.stringWithFormat
import utils.asList
import utils.secondOrNull

actual object Format : StandardLogicOperation {
    private val REGEX = "%[\\d|.]*[sdf]".toRegex()

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
        val formats = REGEX.getFormats(this)
        val rawStrings = this.split(REGEX)

        if (rawStrings.any { it.contains("%") }) {
            throw PercentWithoutFormatSpecifier("'%' sing without format specifier found. Use either '%d', '%f' or '%s'")
        }

        return formats.replaceNullArgsWithStringsAndModifyFormatIfNeeded(args)
            .formatText(rawStrings.toMutableList(), formats)
    }

    private fun Regex.getFormats(text: String) = findAll(text)
        .map {
            it.groupValues.first()
        }.toMutableList()

    private fun List<Any?>.formatText(rawStrings: MutableList<String>, formats: MutableList<String>): String =
        fold("") { acc: String, argument ->
            val singleFormat = formats.removeFirst()
            val rawString = rawStrings.removeFirst()
            val arg = argument.toString()
            val formattedPart = when {
                singleFormat.contains("d") ->
                    NSString.stringWithFormat(rawString + singleFormat, arg.removeSuffix(".0").toInt())
                singleFormat.contains("f") ->
                    NSString.stringWithFormat(rawString + singleFormat, arg.toDouble())
                else -> NSString.stringWithFormat(rawString + singleFormat, arg.cstr)
            }
            acc + formattedPart
        } + rawStrings.joinToString("")

    // In order to align with Android implementation, nulls have to be replaced with "null" strings and printed
    private fun MutableList<String>.replaceNullArgsWithStringsAndModifyFormatIfNeeded(args: List<Any?>): List<Any> {
        var newArgs = mutableListOf<Any>()
        for (index in 0 until count()) {
            var arg = args.getOrNull(index)
            newArgs.add(index, arg ?: "null")
            if (arg == null) {
                this[index] = "%s"
            }
        }
        return newArgs.toList()
    }
}

class PercentWithoutFormatSpecifier(message: String): Exception(message)
