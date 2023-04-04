package string.compareToDate

import kotlinx.datetime.LocalDate

data class DateParameters(
    val year: Int,
    val month: Int,
    val day: Int
) {
    fun compareTo(other: DateParameters): Int? = kotlin.runCatching {
        localDate().compareTo(other.localDate())
    }.onSuccess {
        return when {
            it > 0 -> 1
            it < 0 -> -1
            else -> 0
        }
    }.getOrNull()

    private fun localDate(): LocalDate = run {
        LocalDate(year, month, day)
    }
}
