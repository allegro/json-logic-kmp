pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}
rootProject.name = "json-logic-kmp"

include(":core-api", ":core", ":operations")
