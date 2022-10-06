// swift-tools-version: 5.6
// The swift-tools-version declares the minimum version of Swift required to build this package.
import PackageDescription

let version = "1.1.0"
let checksum = "250de74807d6c356d04820160318f46e2a4267a774b64dc8b66c944d9bf530ea"

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
