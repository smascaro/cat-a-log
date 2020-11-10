package com.smascaro.listdetail

import android.app.Application
import com.smascaro.listdetail.di.app.AppComponent
import com.smascaro.listdetail.di.app.DaggerAppComponent

class BaseApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }
}