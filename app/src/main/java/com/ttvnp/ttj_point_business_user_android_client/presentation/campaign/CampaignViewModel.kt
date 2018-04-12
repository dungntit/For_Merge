package com.ttvnp.ttj_point_business_user_android_client.presentation.campaign

import com.ttvnp.ttj_point_business_user_android_client.model.ShopModel
import com.ttvnp.ttj_point_business_user_android_client.network.repository.CampaignRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class CampaignViewModel @Inject constructor(private val repo: CampaignRepository) {
    val disposable = CompositeDisposable()

    fun getCampaigns(completion: (campaigns: List<ShopModel>) -> Unit) {
        repo.getCampaigns()
                .subscribe { campaigns -> completion(campaigns) }
                .addTo(disposable)
    }

}