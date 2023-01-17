// swift-tools-version: 5.6
// The swift-tools-version declares the minimum version of Swift required to build this package.
import PackageDescription

let version = "1.1.5"
let checksum = "780f0c63a01853eb76f4e193cfa85ffbd2d333a199a418fac9f83421a9a2fcc4"

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
