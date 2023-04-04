@file:Suppress("SpellCheckingInspection")

object LibConfig {
    const val group = "pl.allegro.mobile"
    const val repositoryUrl = "https://github.com/allegro/json-logic-kmp"
    const val name = "JsonLogicKMP"
    const val artifactName = "json-logic"
    const val xcFrameworkName = "JsonLogicKMP"
    const val bundleId = "pl.allegro.mobile.JsonLogicKMP"
}

object Modules {
    const val core = ":core"
    const val operationsApi = ":operations-api"
    const val operationsStdlib = ":operations-stdlib"
    const val utils = ":utils"
    const val kotlinTest = "test"
}

object External {
    const val dateTime = "org.jetbrains.kotlinx:kotlinx-datetime:0.4.0"
}

object Conventions {
    const val versioning = "versioning-convention"
    const val junit = "junit-convention"
    const val publishing = "publishing-convention"
}
