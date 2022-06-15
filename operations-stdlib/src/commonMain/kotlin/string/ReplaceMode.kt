package string

internal data class ReplaceData(
    val replaceCandidate: String,
    val oldString: String,
    val newString: String,
)

internal sealed class ReplaceMode: ()->Any? {
    abstract val replaceData: ReplaceData

    companion object {
        fun from(mode: String, replaceData: ReplaceData) = when {
            mode == AllReplace.name -> {
                AllReplace(replaceData)
            }
            mode.toIntOrNull() != null -> {
                FewReplace(replaceData, mode.toInt())
            }
            else -> throw IllegalArgumentException(mode)
        }
    }
}

private class AllReplace(override val replaceData: ReplaceData) : ReplaceMode() {
    override fun invoke() =
        replaceData.replaceCandidate.replace(replaceData.oldString, replaceData.newString)

    companion object {
        const val name = "all"
    }

}

private class FewReplace(override val replaceData: ReplaceData, val times: Int):ReplaceMode() {
    override fun invoke() =
        replaceData.replaceCandidate.replace(replaceData.oldString, replaceData.newString, times)
}

private fun String.replace(oldValue:String, newValue:String, times: Int) : String {
    return if (times > 0) {
        replaceFirst(oldValue, newValue)
            .replace(oldValue, newValue, times - 1)
    } else {
        this
    }
}
