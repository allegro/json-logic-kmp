@Suppress("DSL_SCOPE_VIOLATION") // TODO remove on fix: https://youtrack.jetbrains.com/issue/KTIJ-19369
plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotest)
    id(Conventions.junit)
    id(Conventions.publishing)
}

kotlin {
    jvm {
        mavenPublication { setFullModuleArtifactId() }
        compilations.all {
            kotlinOptions.jvmTarget = JavaVersion.VERSION_11.majorVersion
        }
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(Modules.operationsApi))
                implementation(project(Modules.utils))
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin(Modules.kotlinTest))
                implementation(libs.bundles.common.kotest)
                implementation(project(Modules.core))
                implementation(project(Modules.utils))
            }
        }
        val jvmMain by getting
        val jvmTest by getting {
            dependsOn(commonTest)
            dependencies {
                implementation(libs.kotest.jvm.junit5.runner)
            }
        }
        val iosX64Main by getting
        val iosX64Test by getting
        val iosArm64Main by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Main by getting
        val iosSimulatorArm64Test by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}
