import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform") version Versions.kotlin
    id("io.kotest.multiplatform") version Versions.kotest apply false
    // common code for publishing
    id("maven-publish")
    id("signing")
    id("pl.allegro.tech.build.axion-release")
//    id("io.github.gradle-nexus.publish-plugin")
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
// common code for publication
apply(from = "../versionConfig.gradle")

group = LibConfig.group
version = scmVersion.version

val javadocJar = tasks.register("javadocJar", Jar::class.java) {
    archiveClassifier.set("javadoc")
}

publishing {
    publications.withType<MavenPublication> {
        artifact(javadocJar)

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
                    name.set("Marek Krogulski")
                    email.set("marek.krogulski@allegro.pl")
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

//nexusPublishing {
//    repositories {
//        sonatype {
//            username.set(System.getenv("SONATYPE_USERNAME"))
//            password.set(System.getenv("SONATYPE_PASSWORD"))
//        }
//    }
//}

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
