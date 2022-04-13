/*
We need to disable this task, because it fails with following error:
`the path does not point to a valid debug symbols file`.
Currently we are using only Release XCFramework.
It could be fixed also by `isStatic = false` but we want to get static lib.
 */
tasks.getByName("assembleJsonLogicKMPDebugXCFramework").enabled = false
