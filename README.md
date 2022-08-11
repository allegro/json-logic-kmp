JsonLogicKMP
============
Kotlin multiplatform [JsonLogic](https://jsonlogic.com/) expressions evaluation engine. Targets iOS and JVM (also Android). The JsonLogic format is designed to allow you to share rules (logic) between front-end and back-end code

Download
--------
For Gradle build system:

```gradle
repositories {
    mavenCentral()
}

dependencies {
// TODO add dynamic versioning
    // JVM & Android targets
    implementation "pl.allegro.mobile:json-logic-core-jvm:0.2.14"
    implementation "pl.allegro.mobile:json-logic-operations-stdlib-jvm:0.2.14"
    // KMP target
    implementation "pl.allegro.mobile:json-logic-core:0.2.14"
    implementation "pl.allegro.mobile:json-logic-operations-stdlib:0.2.14"
}
```
Using CocoaPods:

[CocoaPods](https://cocoapods.org) is a dependency manager for Cocoa projects. For usage and installation instructions, visit their website. To integrate **JsonLogicKMP** into your Xcode project using CocoaPods, specify it in your Podfile:

```ruby
pod 'JsonLogicKMP'
```

How do I initialize the engine?
-------------------
Logic evalautor is initialized by simply building it. All of the json-logic standard operations are added by default. 

**Log** operation is an exception which requires a callback function for logging values. It's added by special function *addLogger()*.

```kotlin
val jsonLogicLoggingCallback: (Any?) -> Unit = { Log.d("JsonLogicEngine-log: $it") }

fun initializeJsonLogicEngine(): JsonLogicEngine {
    return JsonLogicEngine
        .Builder()
        .addLogger(jsonLogicLoggingCallback)
        .build()    
}

```

To expand the possibilities of the engnine add our additional standard library with more Kotlin based operators. To do it use **OperationsProvider** and its ready-to-use operations sets.
```kotlin
fun initializeJsonLogicEngine(): JsonLogicEngine {
    return JsonLogicEngine
        .Builder()
        .addStandardOperations(OperationsProvider.standardOperations)
        .addFunctionalOperations(OperationsProvider.functionalOperations)
        .build()
}
```
Json logic expressions evaluation
-------------------


Custom operations
-------------------
There is a possibility to create more json logic operations with simple api available in **operations-api** module.

Using gradle just add proper dependency and write your own logic:
```gradle
repositories {
    mavenCentral()
}

dependencies {
// TODO add dynamic versioning
    // JVM & Android targets
    implementation "pl.allegro.mobile:json-logic-operations-api-jvm:0.2.14"
    // KMP target
    implementation "pl.allegro.mobile:json-logic-operations-api:0.2.14"
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

To add custom operations use:
```kotlin
fun initializeJsonLogicEngineWithCustomOperations(): JsonLogicEngine {
    return JsonLogicEngine
        .Builder()
        .addStandardOperation(operationName = "size", operation = Size)
        .addFunctionalOperation(operationName = "find", operation = Find)
        .build()
}
```
## License

**JsonLogicKMP** is published under [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).
