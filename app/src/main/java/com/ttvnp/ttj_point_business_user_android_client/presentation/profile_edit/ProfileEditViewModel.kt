package com.ttvnp.ttj_point_business_user_android_client.presentation.profile_edit

import com.ttvnp.ttj_point_business_user_android_client.network.repository.ProfileRepository
import com.ttvnp.ttj_point_business_user_android_client.model.ProfileModel
import android.databinding.BaseObservable
import com.ttvnp.ttj_point_business_user_android_client.model.ImageModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject


class ProfileEditViewModel @Inject constructor(private val repo: ProfileRepository) {
    val model: ObservableModel by lazy { ObservableModel() }
    val disposable = CompositeDisposable()

    fun getProfile(completion: (profile: ProfileModel) -> Unit) {
        repo.getProfile()
                .subscribe { profile -> completion(profile) }
                .addTo(disposable)
    }

    fun updateProfile(profile: ProfileModel, completion: () -> Unit) {
        repo.updateProfile(profile)
                .subscribe { completion() }
                .addTo(disposable)
    }

    fun uploadImage(imageBytes: ByteArray, completion: (image: ImageModel) -> Unit) {
        repo.uploadImage(imageBytes)
                .subscribe { image -> completion(image) }
                .addTo(disposable)
    }

    class ObservableModel : BaseObservable() {
        var firstName = ""
        var middleName = ""
        var lastName = ""
        var icon: ImageModel? = null
        var iconId: Int? = null
    }
}