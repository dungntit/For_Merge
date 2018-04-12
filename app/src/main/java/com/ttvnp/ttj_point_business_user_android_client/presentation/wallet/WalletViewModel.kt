package com.ttvnp.ttj_point_business_user_android_client.presentation.wallet

import com.ttvnp.ttj_point_business_user_android_client.model.ProfileModel
import com.ttvnp.ttj_point_business_user_android_client.network.repository.ProfileRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class WalletViewModel @Inject constructor(private val repo: ProfileRepository) {
    val disposable = CompositeDisposable()

    fun getProfile(completion: (profile: ProfileModel) -> Unit) {
        repo.getProfile()
                .subscribe { profile -> completion(profile) }
                .addTo(disposable)
    }
}