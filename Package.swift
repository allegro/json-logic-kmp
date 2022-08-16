// swift-tools-version: 5.6
// The swift-tools-version declares the minimum version of Swift required to build this package.
import PackageDescription

let version = "0.3.1"
let checksum = "75bdc077153c0c110128456a1360496fa2a3f0cc9406c67b73ce05e52bf482a4"

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
