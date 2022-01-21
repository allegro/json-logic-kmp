import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform") version "1.6.10"
    id("maven-publish")
    id("java-library")
    id("signing")
    id("pl.allegro.tech.build.axion-release") version "1.13.6"
    id("io.gitlab.arturbosch.detekt") version "1.19.0"
    id("io.github.gradle-nexus.publish-plugin") version "1.0.0"
}

apply(from = "versionConfig.gradle")

group = "pl.allegro.mobile"
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
                    baseName = "JsonLogicKMP"
                    isStatic = true
                }
            }
        }
    }
    sourceSets {
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
                name.set("JsonLogicKMP")
                description.set("Kotlin multiplatform JsonLogic expressions evaluation engine")
                url.set("https://github.com/allegro/json-logic-kmp")
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
                        name.set("Rafał Kapela")
                    }
                }
                scm {
                    connection.set("scm:svn:https://github.com/allegro/json-logic-kmp.git")
                    developerConnection.set("scm:git@github.com:allegro/json-logic-kmp.git")
                    url.set("https://github.com/allegro/json-logic-kmp")
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
