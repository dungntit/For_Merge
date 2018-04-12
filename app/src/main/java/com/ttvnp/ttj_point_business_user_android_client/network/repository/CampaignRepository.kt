package com.ttvnp.ttj_point_business_user_android_client.network.repository

import com.ttvnp.ttj_point_business_user_android_client.model.ShopModel
import com.ttvnp.ttj_point_business_user_android_client.network.ApiService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CampaignRepository @Inject constructor(private val api: ApiService) {
    fun getCampaigns(): Single<List<ShopModel>> {
        return api.getCampaigns()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getCampaign(campaignId: String): Single<ShopModel> {
        return api.getCampaign(campaignId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}
