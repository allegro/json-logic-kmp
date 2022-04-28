import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform") version Versions.kotlin
    id("io.kotest.multiplatform") version Versions.kotest
    id(Conventions.junit)
    id(Conventions.publishing)
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = JavaVersion.VERSION_11.majorVersion
        }
    }

    val xcFramework = XCFramework(Modules.XCFrameworkNames.operationsApi)
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = Modules.XCFrameworkNames.operationsApi
            isStatic = true
            xcFramework.add(this)
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(Modules.utils))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(Libs.Kotest.assertionsCore)
                implementation(Libs.Kotest.frameworkEngine)
                implementation(Libs.Kotest.frameworkDataset)
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(Libs.Kotest.jvmJunit5Runner)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}
