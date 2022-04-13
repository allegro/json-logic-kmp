pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}
rootProject.name = "json-logic-kmp"

includeBuild("build-logic")
include("core", "operations-api", "operations-stdlib")
