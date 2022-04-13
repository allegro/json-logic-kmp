@file:Suppress("SpellCheckingInspection")

object LibConfig {
    const val group = "pl.allegro.mobile"
    const val repositoryUrl = "https://github.com/allegro/json-logic-kmp"
    const val name = "JsonLogicKMP"
}

object Modules {
    const val operationsApi = ":operations-api"
}

object Versions {
    const val kotlin = "1.6.10"
    const val axion = "1.13.6"
    const val detekt = "1.19.0"
    const val nexus = "1.0.0"
    const val kotlinxSerialization = "1.3.2"
    const val kotest = "5.0.2"
    const val crashkios = "0.4.0"
    const val kotlinxDatetime = "0.3.2"
}

object Libs {
    object KotlinX {
        const val serializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinxSerialization}"
        const val datetime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.kotlinxDatetime}"
    }

    object Axion {
        const val plugin = "pl.allegro.tech.build:axion-release-plugin:${Versions.axion}"
    }

    object Kotest {
        const val assertionsCore = "io.kotest:kotest-assertions-core:${Versions.kotest}"
        const val frameworkEngine = "io.kotest:kotest-framework-engine:${Versions.kotest}"
        const val frameworkDataset = "io.kotest:kotest-framework-datatest:${Versions.kotest}"
        const val jvmJunit5Runner = "io.kotest:kotest-runner-junit5-jvm:${Versions.kotest}"
    }

    object TouchLab {
        const val crashkios = "co.touchlab:crashkios:${Versions.crashkios}"
    }
}

object Conventions {
    const val junit = "junit-convention"
    const val publishing = "publishing-convention"
    const val xcframeworkAssemble = "xcframework-assemble-convention"
}
