plugins {
    `kotlin-dsl`
}

repositories {
    // TODO remove before first public release
    mavenLocal()
    mavenCentral()
}

dependencies {
    //TODO Introduce version catalogue approach and extract it: https://docs.gradle.org/current/userguide/platforms.html
    api("pl.allegro.tech.build:axion-release-plugin:1.13.6")
}
