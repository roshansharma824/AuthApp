package com.example.authapp

interface Platform {
    val name: String
}

interface GoogleLogin {
    val message: String
    val token: String
}

expect fun getPlatform(): Platform

expect object AppContext

expect fun handleButtonClick(onClick: () -> Unit): GoogleLogin

// In shared module (commonMain)
expect fun openGoogleLogin(onResponse: (message:String,token:String) -> Unit)
