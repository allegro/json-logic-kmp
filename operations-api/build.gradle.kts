import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform") version Versions.kotlin
    id("io.kotest.multiplatform") version Versions.kotest apply false
    id("publishing-convention")
    id("junit-convention")
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = JavaVersion.VERSION_11.majorVersion
        }
    }

    val xcFramework = XCFramework(LibConfig.name)
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = LibConfig.name
            isStatic = true
            xcFramework.add(this)
        }
    }
    sourceSets {
        val commonMain by getting
        val commonTest by getting
        val jvmTest by getting
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
