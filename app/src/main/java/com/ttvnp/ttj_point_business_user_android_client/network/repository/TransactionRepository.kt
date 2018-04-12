package com.ttvnp.ttj_point_business_user_android_client.network.repository

import com.ttvnp.ttj_point_business_user_android_client.model.TransactionModel
import com.ttvnp.ttj_point_business_user_android_client.model.UsePointModel
import com.ttvnp.ttj_point_business_user_android_client.network.ApiService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TransactionRepository @Inject constructor(private val api: ApiService) {
    fun usePoint(amount: Long): Single<TransactionModel> {
        return api.usePoint(UsePointModel(point = amount))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}