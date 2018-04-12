package com.ttvnp.ttj_point_business_user_android_client

import android.util.Log
import com.ttvnp.ttj_point_business_user_android_client.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import io.reactivex.plugins.RxJavaPlugins

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
                .application(this)
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        setupRxErrorHandler()
    }

    private fun setupRxErrorHandler() {
        RxJavaPlugins.setErrorHandler { e: Throwable -> Log.e(TAG, e.toString()) }
    }

    companion object {
        val TAG: String = App::class.java.simpleName
    }
}
