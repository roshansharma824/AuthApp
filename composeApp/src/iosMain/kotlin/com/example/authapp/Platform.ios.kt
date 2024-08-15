package com.example.authapp

import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

// In iOS-specific module (iosMain)
//actual fun handleButtonClick(message: String, token: String) {
//    // Platform-specific code for iOS
//    println("iOS Button Clicked with $message and $token")
//}

class IOSPlatformGoogle : GoogleLogin {
    override val message: String = ""
    override val token: String = ""
}

actual fun handleButtonClick(
    onClick: () -> Unit
): GoogleLogin = IOSPlatformGoogle()

actual fun openGoogleLogin(onResponse: (message:String,token:String) -> Unit) {

}

actual object AppContext
