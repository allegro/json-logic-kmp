plugins {
    /* TODO fix when version catalog will be available for precompiled scripts:
        https://github.com/gradle/gradle/issues/15352, https://github.com/gradle/gradle/issues/15383 */
    id("pl.allegro.tech.build.axion-release")
}

apply(from = "$rootDir/buildSrc/versionConfig.gradle")

group = LibConfig.group
version = scmVersion.version
