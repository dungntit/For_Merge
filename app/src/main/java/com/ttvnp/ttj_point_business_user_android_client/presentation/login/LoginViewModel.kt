package com.ttvnp.ttj_point_business_user_android_client.presentation.login

import android.content.Context
import android.databinding.BaseObservable
import android.preference.PreferenceManager
import com.ttvnp.ttj_point_business_user_android_client.network.repository.AuthRepository
import com.ttvnp.ttj_point_business_user_android_client.util.PreferenceKey
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class LoginViewModel @Inject constructor(context: Context, private val repo: AuthRepository) {
    val model: ObservableModel by lazy { ObservableModel() }
    val disposable = CompositeDisposable()
    private val pref = PreferenceManager.getDefaultSharedPreferences(context)

    fun isLoggedIn(): Boolean {
        return pref.getString(PreferenceKey.AUTH_TOKEN, "").isNotBlank()
    }

    fun login(completion: () -> Unit) {
        repo.login(model.email, model.password)
                .subscribe { _ -> completion() }
                .addTo(disposable)
    }

    fun signUp(completion: () -> Unit) {
        repo.signUp(model.email, model.password)
                .subscribe(completion)
                .addTo(disposable)
    }

    class ObservableModel : BaseObservable() {
        var email = ""
        var password = ""
    }
}

