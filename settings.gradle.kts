pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}
rootProject.name = "json-logic-kmp"

include("core", "operations-api", "operations-stdlib", "utils", "umbrella-framework")
