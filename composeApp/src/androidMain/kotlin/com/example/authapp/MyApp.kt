package com.example.authapp

import android.app.Application
import android.content.Context

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppContext.setUp(applicationContext)
    }

//    override fun attachBaseContext(base: Context) {
//        super.attachBaseContext(
//            LocaleManager.getLocalizedContext(base)
//        )
//    }
}