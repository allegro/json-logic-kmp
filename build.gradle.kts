import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import io.gitlab.arturbosch.detekt.Detekt

plugins {
    kotlin("multiplatform") version "1.6.10"
    id("maven-publish")
    id("pl.allegro.tech.build.axion-release") version "1.13.6"
    id("io.gitlab.arturbosch.detekt") version "1.19.0"

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
}

detekt {
    autoCorrect = true
    ignoreFailures = false
}

tasks.withType<Detekt>().configureEach {
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}
