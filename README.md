JsonLogicKMP
============
Kotlin multiplatform [JsonLogic](https://jsonlogic.com/) expressions evaluation engine. Targets iOS and JVM (also Android). The JsonLogic format is designed to allow you to share rules (logic) between front-end and back-end code.

Download
--------
For Gradle build system:

```gradle
repositories {
    mavenCentral()
}

dependencies {
    // JVM & Android targets
    implementation "pl.allegro.mobile:json-logic-core-jvm:1.0.0"
    implementation "pl.allegro.mobile:json-logic-operations-stdlib-jvm:1.0.0"
    // KMP target
    implementation "pl.allegro.mobile:json-logic-core:1.0.0"
    implementation "pl.allegro.mobile:json-logic-operations-stdlib:1.0.0"
}
```
with CocoaPods:

To integrate **JsonLogicKMP** into your Xcode project using CocoaPods, specify it in your Podfile:

```ruby
pod 'JsonLogicKMP'
```

How to initialize the engine?
-------------------
Logic evalautor is initialized by simply building it. All of the json-logic standard operations are added by default. 

**Log** operation is an exception which requires a callback function to log values. It's added by special function *addLogger()*.

Initialization in Kotlin:

```kotlin
val jsonLogicLoggingCallback: (Any?) -> Unit = { Log.d("JsonLogicEngine-log: $it") }

fun buildJsonLogicEngineWithLogger(): JsonLogicEngine {
    return JsonLogicEngine
        .Builder()
        .addLogger(jsonLogicLoggingCallback)
        .build()    
}
```

and Swift:

```swift
    let logger = Logger(subsystem: Bundle.moduleBundle.bundleIdentifier ?? Bundle.moduleName, category: Self.loggerCategory)
    
    let jsonLogicLoggingCallback: (Any?) -> Void = {
        guard let message = $0 else { return }
        logger.debug("\(String(describing: message), privacy: .private)")
    }
    
    func buildJsonLogicEngineWithLogger(): JsonLogicEngine = JsonLogicKMP.JsonLogicEngineBuilder()
        .addLogger(loggingCallback: jsonLogicLoggingCallback)
        .build()
```

To expand the possibilities of the engnine add our additional standard library with more Kotlin based operators. To do it use **OperationsProvider** and its ready-to-use operations sets.

On Kotlin target:

```kotlin
fun buildJsonLogicEngineWithStandardOperations(): JsonLogicEngine {
    return JsonLogicEngine
        .Builder()
        .addStandardOperations(OperationsProvider.standardOperations)
        .addFunctionalOperations(OperationsProvider.functionalOperations)
        .build()
}
```

and Swift one:

```swift
    func buildJsonLogicEngineWithLogger(): JsonLogicEngine {
        return JsonLogicKMP.JsonLogicEngineBuilder()
            .addStandardOperations(operations: OperationsProvider.shared.standardOperations)
            .addFunctionalOperations(operations: OperationsProvider.shared.functionalOperations)
            .build()
    }
```

Json logic expressions evaluation
-------------------

Logic expressions are represented by maps where key is operation name and value is arguments. User might provide some additional input using **data** parameter. 

Example expression evaluation in Kotlin is shown below:

```kotlin
fun evaluateAdditionOperation(): JsonLogicResult {
    val logicEngine = JsonLogicEngine.Builder().build()
    val expression = mapOf("+" to listOf(1, mapOf("var" to "second")))
    val data = mapOf("second" to 2)
    
    return logicEngine.evaluate(expression, data)
}
```

and in Swift:

```swift
func evaluateAdditionOperation() -> JsonLogicResult {
    let engine = JsonLogicKMP.JsonLogicEngineBuilder().build()
    let logicExpression: [String: Any] = ["+": [1, ["var": "second"]]]
    let logicData: [String: Any] = ["second": 2]
        
    return engine.evaluate(expression: logicExpression, data: logicData)
}
```

Expressions evaluation results in **JsonLogicResult**. It indicates if evaluation is successful or what kind of error occured.

Custom operations
-------------------
There is a possibility to create more json logic operations with simple api available in **operations-api** module.

Using gradle just add proper dependency and write your own logic:

```gradle
repositories {
    mavenCentral()
}

dependencies {
    // JVM & Android targets
    implementation "pl.allegro.mobile:json-logic-operations-api-jvm:1.0.0"
    // KMP target
    implementation "pl.allegro.mobile:json-logic-operations-api:1.0.0"
}
```

For simple operation that doesn't evaluate any json logic expressions internally, use **StandardLogicOperation** interface:

```kotlin
object Size : StandardLogicOperation {
    override fun evaluateLogic(expression: Any?, data: Any?): Any? = (expression as? List<*>)?.size
}
```

For more complex operations use **FunctionalLogicOperation** interface:

```kotlin
object Find : FunctionalLogicOperation, EvaluatingUnwrapper {
    override fun evaluateLogic(expression: Any?, data: Any?, evaluator: LogicEvaluator): Any? {
        return expression.asList.let { expressionValues ->
            val inputData = unwrapDataByEvaluation(expressionValues, data, evaluator)
            val predicateOperation = expressionValues.getMappingOperationOrNull()

            predicateOperation?.let {
                inputData?.find { evaluator.evaluateLogic(predicateOperation, it) == true }
            }
        }
    }
}
```

Main difference between these two is that functional operations has access to some **LogicEvaluator** instance. It allows them to evaluate expressions internally.

To add custom operations in Kotlin implementation use:

```kotlin
fun initializeJsonLogicEngineWithCustomOperations(): JsonLogicEngine {
    return JsonLogicEngine
        .Builder()
        .addStandardOperation(operationName = "size", operation = Size)
        .addFunctionalOperation(operationName = "find", operation = Find)
        .build()
}
```

or in Swift:

```swift
func initializeJsonLogicEngineWithCustomOperations() -> JsonLogicEngine {
    return JsonLogicKMP.JsonLogicEngineBuilder()
        .addStandardOperation(operationName: "size", operation: Size.shared)
        .addFunctionalOperation(operationName: "find", operation: Find.shared)
        .build()
}
```

## License

**JsonLogicKMP** is published under [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).
