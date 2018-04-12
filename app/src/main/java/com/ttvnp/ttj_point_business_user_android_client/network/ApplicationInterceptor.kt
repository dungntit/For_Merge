package com.ttvnp.ttj_point_business_user_android_client.network

import android.content.Context
import android.preference.PreferenceManager
import com.ttvnp.ttj_point_business_user_android_client.util.PreferenceKey
import okhttp3.Interceptor
import okhttp3.Response

class ApplicationInterceptor(context: Context) : Interceptor {
    private val pref = PreferenceManager.getDefaultSharedPreferences(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val requestBuilder = original.newBuilder().apply {
            addHeader("Accept", "application/json")
            addHeader("Content-type", "application/json")
            val token = pref.getString(PreferenceKey.AUTH_TOKEN, "")
            if (token.isNotBlank()) {
                addHeader("Authorization", "Token $token")
            }
            method(original.method(), original.body())
        }

        return chain.proceed(requestBuilder.build())
    }
}