import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform") version Versions.kotlin
    id("maven-publish")
    id("java-library")
    id("signing")
    id("pl.allegro.tech.build.axion-release") version Versions.axion
    id("io.gitlab.arturbosch.detekt") version Versions.detekt
    id("io.github.gradle-nexus.publish-plugin") version Versions.nexus
    kotlin("plugin.serialization") version Versions.kotlin
}

apply(from = "versionConfig.gradle")

group = LibConfig.group
version = scmVersion.version

repositories {
    google()
    mavenCentral()
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = JavaVersion.VERSION_11.majorVersion
        }
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }

        val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
            if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
                ::iosArm64
            else
                ::iosX64

        iosTarget("ios") {
            binaries {
                framework {
                    baseName = LibConfig.name
                    isStatic = true
                }
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Libs.KotlinX.serializationJson)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

detekt {
    buildUponDefaultConfig = true
    autoCorrect = true
    source = files("src/")
    ignoreFailures = false
    config = files("$projectDir/config/detekt/detekt.yml")
}

tasks.withType<Detekt>().configureEach {
    reports {
        html.required.set(true)
        xml.required.set(true)
        txt.required.set(true)
        sarif.required.set(true)
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

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
                        id.set("GypsyBox")
                        name.set("Rafal Kapela")
                    }
                    developer {
                        id.set("PaulinaSadowskaAllegro")
                        name.set("Paulina Sadowska")
                    }
                    developer {
                        id.set("mkrogulskiallegro2 ")
                        name.set("Marek Krogulski")
                    }
                    developer {
                        id.set("pavlo-davydiuk")
                        name.set("Pavlo Davydiuk")
                    }
                    developer {
                        id.set("tgebarowski")
                        name.set("Tomasz Gebarowski")
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
