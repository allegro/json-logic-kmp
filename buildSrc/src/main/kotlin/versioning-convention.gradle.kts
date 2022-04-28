plugins {
    id("pl.allegro.tech.build.axion-release")
}

apply(from = "$rootDir/buildSrc/versionConfig.gradle")

group = LibConfig.group
version = scmVersion.version
