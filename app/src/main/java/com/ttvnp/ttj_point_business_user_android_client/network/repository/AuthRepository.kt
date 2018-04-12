package com.ttvnp.ttj_point_business_user_android_client.network.repository

import android.content.Context
import android.preference.PreferenceManager
import com.ttvnp.ttj_point_business_user_android_client.BuildConfig
import com.ttvnp.ttj_point_business_user_android_client.model.CustomerAuthModel
import com.ttvnp.ttj_point_business_user_android_client.network.ApiService
import com.ttvnp.ttj_point_business_user_android_client.util.PreferenceKey
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class AuthRepository @Inject constructor(private val context: Context,
                                         private val api: ApiService) {
    fun login(email: String, password: String): Single<CustomerAuthModel> {
        return api.login(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess {
                    val token = if (BuildConfig.FLAVOR == "jsonserver") "dummy_token" else it.token
                    val pref = PreferenceManager.getDefaultSharedPreferences(context)
                    pref.edit().putString(PreferenceKey.AUTH_TOKEN, token).apply()
                }
    }

    fun logout() {
        val pref = PreferenceManager.getDefaultSharedPreferences(context)
        pref.edit().putString(PreferenceKey.AUTH_TOKEN, "").apply()
    }

    fun signUp(email: String, password: String): Completable {
        return api.signUp(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun activate(email: String, activationCode: String): Completable {
        return api.activate(email, activationCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}