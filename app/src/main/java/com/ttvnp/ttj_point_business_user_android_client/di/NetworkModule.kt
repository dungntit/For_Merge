package com.ttvnp.ttj_point_business_user_android_client.di

import android.content.Context
import com.squareup.moshi.Moshi
import com.ttvnp.ttj_point_business_user_android_client.BuildConfig
import com.ttvnp.ttj_point_business_user_android_client.network.ApiService
import com.ttvnp.ttj_point_business_user_android_client.network.ApplicationInterceptor
import com.ttvnp.ttj_point_business_user_android_client.network.ApplicationJsonAdapterFactory
import com.ttvnp.ttj_point_business_user_android_client.network.ResponseInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton @Provides
    fun provideHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(ApplicationInterceptor(context))
                .addInterceptor(ResponseInterceptor(context))
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                })
                .build()
    }

    @Singleton @Provides
    fun provideApiService(client: OkHttpClient): ApiService {
        return Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder()
                        .add(ApplicationJsonAdapterFactory.INSTANCE)
                        .build()))
                .baseUrl(BuildConfig.BaseUrl)
                .build()
                .create(ApiService::class.java)
    }
}