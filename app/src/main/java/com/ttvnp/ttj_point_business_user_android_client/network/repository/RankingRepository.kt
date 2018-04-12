package com.ttvnp.ttj_point_business_user_android_client.network.repository

import com.ttvnp.ttj_point_business_user_android_client.model.PrizeItemModel
import com.ttvnp.ttj_point_business_user_android_client.model.RankingItemModel
import com.ttvnp.ttj_point_business_user_android_client.network.ApiService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RankingRepository @Inject constructor(private val api: ApiService) {
    fun getRanking(): Single<List<RankingItemModel>> {
        return api.getRanking()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
    fun getPrizes(): Single<List<PrizeItemModel>> {
        return api.getPrizes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}