// swift-tools-version: 5.6
// The swift-tools-version declares the minimum version of Swift required to build this package.
import PackageDescription

let version = "0.3.0"
let checksum = "e63cdb0b773a785a3b6668c5cb813dc2b01a15224e45ae870b3a4236f217a8d3"

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
