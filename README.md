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

How do I initialize the engine?
-------------------
Logic evalautor is initialized by simply building it. All of the json-logic standard operations are added by default. 

**Log** operation is an exception which requires a callback function for logging values. It's added by special function *addLogger()*

```kotlin
val jsonLogicLoggingCallback: (Any?) -> Unit = { Log.d("JsonLogicEngine-log: $it") }

fun initializeJsonLogicEngine(): JsonLogicEngine {
    return JsonLogicEngine
        .Builder()
        .addStandardOperations(OperationsProvider.standardOperations)
        .addFunctionalOperations(OperationsProvider.functionalOperations)
        .addLogger(jsonLogicLoggingCallback)
        .build()    
}

```




## License

**JsonLogicKMP** is published under [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).
