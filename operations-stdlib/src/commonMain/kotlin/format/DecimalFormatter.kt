package format

import utils.asList
import utils.secondOrNull

internal interface DecimalFormatter {
    fun formatDecimal(
        expression: Any?,
        data: Any?,
        formatFloatingPoint: (format: String, arg: Double) -> String
    ): String? {
        return with(expression.asList) {
            val format = firstOrNull().toString()
            val formattedArgument = secondOrNull().toString()

            runCatching { format.formatAsFloatingDecimal(formattedArgument, formatFloatingPoint) }
                .fold(
                    onSuccess = { it },
                    onFailure = { null }
                )
        }
    }

    private fun String.formatAsFloatingDecimal(
        formattedArgument: String,
        formatFloatingPoint: (String, Double) -> String
    ) = if (matches("%[\\d|.]*[f]".toRegex())) {
            formattedArgument.toDoubleOrNull()?.let {
                formatFloatingPoint(this, it)
            }
        } else {
            null
        }
}
