import io.gitlab.arturbosch.detekt.Detekt

@Suppress("DSL_SCOPE_VIOLATION") // TODO remove on fix: https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    id("maven-publish")
    id("java-library")
    id("signing")
    alias(libs.plugins.detekt)
    alias(libs.plugins.nexus)
    id(Conventions.versioning)
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

val detektAll by tasks.registering(Detekt::class) {
    description = "Runs over whole code base without the starting overhead for each module."
    parallel = true
    buildUponDefaultConfig = true
    setSource(files(projectDir))
    config.setFrom(files("$projectDir/config/detekt/detekt.yml"))
    include("**/*.kt")
    exclude("**/*.kts")
    exclude("resources/")
    exclude("build/")
    exclude("buildSrc/")
    reports {
        html.required.set(true)
        xml.required.set(true)
        txt.required.set(true)
        sarif.required.set(true)
    }
}

nexusPublishing {
    repositories {
        sonatype {
            username.set(System.getenv("SONATYPE_USERNAME"))
            password.set(System.getenv("SONATYPE_PASSWORD"))
        }
    }
}
