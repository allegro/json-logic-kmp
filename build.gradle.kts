import io.gitlab.arturbosch.detekt.Detekt

plugins {
    id("maven-publish")
    id("java-library")
    id("signing")
    id("io.gitlab.arturbosch.detekt") version Versions.detekt
    id("io.github.gradle-nexus.publish-plugin") version Versions.nexus
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
