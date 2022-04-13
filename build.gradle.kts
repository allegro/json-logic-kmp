import io.gitlab.arturbosch.detekt.Detekt

plugins {
    id("maven-publish")
    id("java-library")
    id("signing")
    id("pl.allegro.tech.build.axion-release") version Versions.axion
    id("io.gitlab.arturbosch.detekt") version Versions.detekt
    id("io.github.gradle-nexus.publish-plugin") version Versions.nexus
}

apply(from = "versionConfig.gradle")

group = LibConfig.group
version = scmVersion.version

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

val javadocJar = tasks.register("javadocJar", Jar::class.java) {
    archiveClassifier.set("javadoc")
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

publishing {
    publications.withType<MavenPublication> {
        artifact(javadocJar)

        pom {
            name.set(LibConfig.name)
            description.set("Kotlin multiplatform JsonLogic expressions evaluation engine")
            url.set(LibConfig.repositoryUrl)
            inceptionYear.set("2022")
            licenses {
                license {
                    name.set("The Apache License, Version 2.0")
                    url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                }
            }
            developers {
                developer {
                    name.set("Marek Krogulski")
                    email.set("marek.krogulski@allegro.pl")
                }
            }
            scm {
                connection.set("scm:svn:${LibConfig.repositoryUrl}")
                developerConnection.set("scm:git@github.com:allegro/json-logic-kmp.git")
                url.set(LibConfig.repositoryUrl)
            }
        }
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

System.getenv("GPG_KEY_ID")?.let { gpgKeyId ->
    signing {
        useInMemoryPgpKeys(
            gpgKeyId,
            System.getenv("GPG_PRIVATE_KEY"),
            System.getenv("GPG_PRIVATE_KEY_PASSWORD")
        )
        sign(publishing.publications)
    }
}
