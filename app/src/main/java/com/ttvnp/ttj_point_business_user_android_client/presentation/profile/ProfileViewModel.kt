package com.ttvnp.ttj_point_business_user_android_client.presentation.profile

import com.ttvnp.ttj_point_business_user_android_client.model.ProfileModel
import com.ttvnp.ttj_point_business_user_android_client.network.repository.AuthRepository
import com.ttvnp.ttj_point_business_user_android_client.network.repository.ProfileRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val repo: ProfileRepository, private val authRepo: AuthRepository) {
    val disposable = CompositeDisposable()

    fun getProfile(completion: (profile: ProfileModel) -> Unit) {
        repo.getProfile()
                .subscribe { profile -> completion(profile) }
                .addTo(disposable)
    }

    fun logout(completion: () -> Unit) {
        authRepo.logout()
        completion()
    }
}