#!/bin/sh

cd ../..
version=$(./gradlew :core:currentVersion -q -Prelease.quiet)
echo "const val VERSION = \"$version\"" > core/src/iosMain/kotlin/Version.kt

echo "$version updated in file"
exit 0

