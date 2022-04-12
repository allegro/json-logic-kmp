plugins {
    `kotlin-dsl`
}

repositories {
    // TODO remove before first public release
    mavenLocal()
    gradlePluginPortal()
}

dependencies {
    //TODO etract version and notation
    api("pl.allegro.tech.build:axion-release-plugin:1.13.6")
}
