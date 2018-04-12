package com.ttvnp.ttj_point_business_user_android_client.network

import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager
import com.ttvnp.ttj_point_business_user_android_client.presentation.login.LoginActivity
import com.ttvnp.ttj_point_business_user_android_client.util.PreferenceKey
import okhttp3.Interceptor
import okhttp3.Response

class ResponseInterceptor(private val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        if (response.code() in 400 until 600) {
            if (response.code() == 401 || response.code() == 404) {
                // force logout
                val pref = PreferenceManager.getDefaultSharedPreferences(context)
                pref.edit().putString(PreferenceKey.AUTH_TOKEN, "").apply()

                // go to login activity
                val intent = Intent(context, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                context.startActivity(intent)
            }
        }

        return response
    }
}