sealed class JsonLogicResult {
    data class Success(val value: Any) : JsonLogicResult()
    data class Failure(val message: String? = null) : JsonLogicResult()
}
