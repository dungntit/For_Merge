package com.ttvnp.ttj_point_business_user_android_client.network.repository

import com.ttvnp.ttj_point_business_user_android_client.model.ImageModel
import com.ttvnp.ttj_point_business_user_android_client.model.ProfileModel
import com.ttvnp.ttj_point_business_user_android_client.network.ApiService
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.RequestBody
import javax.inject.Inject
import okhttp3.MultipartBody



class ProfileRepository @Inject constructor(private val api: ApiService) {
    fun getProfile(): Single<ProfileModel> {
        return api.getProfile()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun updateProfile(profile: ProfileModel): Completable {
        return api.updateProfile(profile)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun uploadImage(imageBytes: ByteArray): Single<ImageModel> {
        val requestFile = RequestBody.create(MediaType.parse("image/jpeg"), imageBytes)
        val body = MultipartBody.Part.createFormData("original", "image.jpg", requestFile)

        return api.uploadImage(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}