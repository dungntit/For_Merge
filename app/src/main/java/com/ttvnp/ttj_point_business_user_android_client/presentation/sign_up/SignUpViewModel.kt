package com.ttvnp.ttj_point_business_user_android_client.presentation.sign_up

import android.content.Context
import android.databinding.BaseObservable
import android.preference.PreferenceManager
import android.widget.Toast
import com.ttvnp.ttj_point_business_user_android_client.network.repository.AuthRepository
import com.ttvnp.ttj_point_business_user_android_client.util.PreferenceKey
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class SignUpViewModel @Inject constructor(private val context: Context, private val repo: AuthRepository) {
    val model: ObservableModel by lazy { ObservableModel() }
    val disposable = CompositeDisposable()
    private val pref = PreferenceManager.getDefaultSharedPreferences(context)

    fun isLoggedIn(): Boolean {
        return pref.getString(PreferenceKey.AUTH_TOKEN, "").isNotBlank()
    }

    fun signUp(completion: () -> Unit) {
        repo.signUp(model.email, model.password)
                .doOnError { Toast.makeText(context, "Error", Toast.LENGTH_LONG).show() }
                .subscribe(completion)
                .addTo(disposable)
    }

    class ObservableModel : BaseObservable() {
        var email = ""
        var password = ""
    }
}

