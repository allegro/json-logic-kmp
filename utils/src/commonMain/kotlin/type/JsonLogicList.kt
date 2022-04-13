package type

class JsonLogicList<T>(private val items: List<T>) : List<T> by items, Comparable<List<T>> {
    override fun compareTo(other: List<T>): Int {
        return compareValues(items.toString(), other.toString())
    }
}
