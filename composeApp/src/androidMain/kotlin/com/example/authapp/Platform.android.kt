package com.example.authapp

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

// In Android-specific module (androidMain)
//actual fun handleButtonClick(message: String, token: String) {
//    // Platform-specific code for Android
//    println("Android Button Clicked with $message and $token")
//}
class AndroidPlatformGoogle() : GoogleLogin {
    override val message: String = ""
    override val token: String = ""

}

actual fun handleButtonClick(
    onClick: () -> Unit
): GoogleLogin = AndroidPlatformGoogle()


actual fun openGoogleLogin(onResponse: (message:String,token:String) -> Unit) {


    signIn(){ message, token ->
        val user = getUserFromTokenId(tokenId = token)
        user?.let { user->
            val json = Json.encodeToString(user)

            onResponse.invoke(json, token)
        }

    }
}
