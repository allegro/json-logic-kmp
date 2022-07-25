package format

import utils.asList
import utils.secondOrNull

internal interface DecimalFormatter {
    fun formatDecimal(
        expression: Any?,
        data: Any?,
        formattingFunction: (sign: String, format: String, arg: String) -> String
    ): String? {
        return with(expression.asList) {
            val format = firstOrNull().toString()
            val args = secondOrNull().asList

            runCatching { format.formatString(args, formattingFunction) }
                .fold(
                    onSuccess = { it },
                    onFailure = { null }
                )
        }
    }

    private fun String.formatString(args: List<Any?>, formattingFunction: (String, String, String) -> String): String {
        val formats = REGEX.getFormats(this)
        val rawStrings = this.split(REGEX)

        if (rawStrings.any { it.contains("%") }) {
            throw PercentWithoutFormatSpecifier("Empty format specifier found. Use only %f")
        }
        return formats.replaceNullArgsWithStringsAndModifyFormatIfNeeded(args)
            .formatText(rawStrings.toMutableList(), formats, formattingFunction)
    }

    // In order to align with Android implementation, nulls have to be replaced with "null" strings and printed
    private fun MutableList<String>.replaceNullArgsWithStringsAndModifyFormatIfNeeded(args: List<Any?>): List<Any> =
        List(size) { index ->
            val arg = args.getOrNull(index)
            if (arg == null) {
                this[index] = "%s"
            }
            arg ?: "null"
        }

    private fun Regex.getFormats(text: String) = findAll(text)
        .map {
            it.groupValues.first()
        }.toMutableList()

    private fun List<Any>.formatText(
        rawStrings: MutableList<String>,
        formats: MutableList<String>,
        formattingFunction: (String, String, String) -> String
    ): String = fold("") { acc: String, argument ->
        val singleFormat = formats.removeFirst()
        val rawString = rawStrings.removeFirst()
        val arg = argument.toString()
        val formattedPart = formattingFunction(singleFormat, rawString + singleFormat, arg)
        acc + formattedPart
    } + rawStrings.joinToString("")

    private companion object {
        val REGEX = "%[\\d|.]*[f]".toRegex()
    }
}

internal class PercentWithoutFormatSpecifier(message: String) : Exception(message)
