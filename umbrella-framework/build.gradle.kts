import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

@Suppress("DSL_SCOPE_VIOLATION") // TODO remove on fix: https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotest)
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
                implementation(libs.bundles.common.kotest)
                implementation(project(Modules.utils))
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(libs.kotest.jvm.junit5.runner)
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
                implementation(libs.crashkios)
            }
        }
    }
}


