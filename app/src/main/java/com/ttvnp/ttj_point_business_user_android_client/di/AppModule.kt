package com.ttvnp.ttj_point_business_user_android_client.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton @Provides
    fun provideContext(application: Application): Context = application
}