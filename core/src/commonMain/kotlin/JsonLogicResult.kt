sealed class JsonLogicResult {
    data class Success(val value: Any) : JsonLogicResult()
    object NullResultFailure : JsonLogicResult()
    object EmptyExpressionFailure : JsonLogicResult()
    object MissingOperationFailure : JsonLogicResult()
}
