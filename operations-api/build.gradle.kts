import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform") version Versions.kotlin
    id("io.kotest.multiplatform") version Versions.kotest
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = JavaVersion.VERSION_11.majorVersion
        }
    }

    // same as above
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
}

/*
We need to disable this task, because it fails with following error:
`the path does not point to a valid debug symbols file`.
Currently we are using only Release XCFramework.
It could be fixed also by `isStatic = false` but we want to get static lib.
 */
tasks.getByName("assembleJsonLogicKMPDebugXCFramework").enabled = false
