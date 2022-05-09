import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform") version Versions.kotlin
    id("io.kotest.multiplatform") version Versions.kotest
    id(Conventions.junit)
    id(Conventions.publishing)
}

kotlin {
    jvm {
        mavenPublication{ setFullModuleArtifactId() }
        compilations.all {
            kotlinOptions.jvmTarget = JavaVersion.VERSION_11.majorVersion
        }
    }

    val xcFramework = XCFramework(LibConfig.xcFrameworkName)
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = LibConfig.xcFrameworkName
            isStatic = true
            export(project(Modules.core))
            export(project(Modules.operationsApi))
            export(project(Modules.operationsStdlib))
            xcFramework.add(this)
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(Modules.core))
                api(project(Modules.operationsApi))
                api(project(Modules.operationsStdlib))
                implementation(project(Modules.utils))
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin(Modules.kotlinTest))
                implementation(Libs.Kotest.assertionsCore)
                implementation(Libs.Kotest.frameworkEngine)
                implementation(Libs.Kotest.frameworkDataset)
                implementation(project(Modules.utils))
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

            dependencies {
                implementation(Libs.TouchLab.crashkios)
            }
        }
    }
}


