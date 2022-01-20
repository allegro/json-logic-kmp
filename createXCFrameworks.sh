#!/usr/bin/env bash

declare -a SDKs=("iphonesimulator" "iphoneos")
declare -a CONFIGURATIONS=("debug" "release")

frameworkName="JsonLogicKMP"

rm -rf "build/bin/ios/xcframeworks"

#create xcframeworks directories and move ios binaries there
for sdk in "${SDKs[@]}"
do
   echo "Build frameworks for $sdk"
   export SDK_NAME=$sdk
   ./gradlew iosBinaries

   for configuration in "${CONFIGURATIONS[@]}"
   do
     mkdir -p "build/bin/ios/xcframeworks/${configuration}/${sdk}"
     mv "build/bin/ios/${configuration}Framework/${frameworkName}.framework" "build/bin/ios/xcframeworks/${configuration}/${sdk}"
   done
done

#create xcframeworks and clean
for configuration in "${CONFIGURATIONS[@]}"
do
    OUTPUT="build/bin/ios/xcframeworks/${configuration}/${frameworkName}.xcframework"
    COMMAND="xcodebuild -create-xcframework -output ${OUTPUT}"
    for sdk in "${SDKs[@]}"
    do
        COMMAND+=" -framework build/bin/ios/xcframeworks/${configuration}/${sdk}/${frameworkName}.framework"
    done

    $COMMAND

    for sdk in "${SDKs[@]}"
    do
        rm -rf "build/bin/ios/xcframeworks/${configuration}/${sdk}"
    done

    rm -rf "build/bin/ios/${configuration}Framework"
done
