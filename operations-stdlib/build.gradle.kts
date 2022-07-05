plugins {
    kotlin("multiplatform") version Versions.kotlin
    id("io.kotest.multiplatform") version Versions.kotest
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
                implementation(Libs.Kotest.assertionsCore)
                implementation(Libs.Kotest.frameworkEngine)
                implementation(Libs.Kotest.frameworkDataset)
                implementation(project(Modules.core))
                implementation(project(Modules.utils))
            }
        }
        val jvmMain by getting
        val jvmTest by getting {
            dependsOn(commonTest)
            dependencies {
                implementation(Libs.Kotest.jvmJunit5Runner)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosX64Test by getting
        val iosArm64Test by getting
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
