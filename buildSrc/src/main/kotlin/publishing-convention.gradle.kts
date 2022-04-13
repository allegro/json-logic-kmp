import org.gradle.kotlin.dsl.`maven-publish`
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.signing
import org.gradle.kotlin.dsl.withType

plugins {
    `maven-publish`
    signing
    id("pl.allegro.tech.build.axion-release")
}

apply(from = "../versionConfig.gradle")

group = LibConfig.group
version = scmVersion.version

val javadocJar: TaskProvider<Jar> = tasks.register("javadocJar", Jar::class.java) {
    archiveClassifier.set("javadoc")
}

configure<PublishingExtension> {
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
