package com.ttvnp.ttj_point_business_user_android_client.presentation.activate

import android.content.Context
import android.databinding.BaseObservable
import android.util.Log
import com.ttvnp.ttj_point_business_user_android_client.network.repository.AuthRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class ActivateViewModel @Inject constructor(private val context: Context, private val repo: AuthRepository) {
    val model: ObservableModel by lazy { ObservableModel() }
    val disposable = CompositeDisposable()

    fun activate(completion: () -> Unit) {
        repo.activate(model.email, model.activationCode)
                .subscribe(completion)
                .addTo(disposable)
    }

    fun login(completion: () -> Unit) {
        repo.login(model.email, model.password)
                .subscribe { _ -> completion() }
                .addTo(disposable)
    }

    class ObservableModel : BaseObservable() {
        var email = ""
        var password = ""
        var activationCode = ""
    }
}
