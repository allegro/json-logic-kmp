// swift-tools-version: 5.6
// The swift-tools-version declares the minimum version of Swift required to build this package.
import PackageDescription

let version = "1.1.5"
let checksum = "50992e0adb4a2b007664a5487b9ae729ced3249196017b42f9d7af5317d53ccc"

let package = Package(
    name: "JsonLogicKMP",
    products: [
        .library(
            name: "JsonLogicKMP",
            targets: ["JsonLogicKMP"]),
    ],
    targets: [
        .binaryTarget(
            name: "JsonLogicKMP",
            url: "https://github.com/allegro/json-logic-kmp/releases/download/\(version)/JsonLogicKMP.xcframework.zip",
            checksum: "\(checksum)"
        ),
    ]
)
