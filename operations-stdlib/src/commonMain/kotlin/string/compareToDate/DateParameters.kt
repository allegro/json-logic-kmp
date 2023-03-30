package string.compareToDate

internal data class DateParameters(
    val year: Int,
    val month: Int,
    val day: Int
) {
    fun compareTo(other: DateParameters) = when {
        year.compareTo(other.year) != 0 -> year.compareTo(other.year)
        month.compareTo(other.month) != 0 -> month.compareTo(other.month)
        else -> day.compareTo(other.day)
    }
}
