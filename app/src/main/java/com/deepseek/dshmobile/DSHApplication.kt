package com.deepseek.dshmobile

import android.app.Application
import com.deepseek.dshmobile.di.AppContainer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DSHApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppContainer.init(this)
    }
}
