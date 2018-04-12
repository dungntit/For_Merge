package com.ttvnp.ttj_point_business_user_android_client.presentation.ranking

import com.ttvnp.ttj_point_business_user_android_client.model.PrizeItemModel
import com.ttvnp.ttj_point_business_user_android_client.network.repository.RankingRepository
import com.ttvnp.ttj_point_business_user_android_client.model.RankingItemModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class RankingViewModel @Inject constructor(private val repo: RankingRepository) {
    val disposable = CompositeDisposable()

    fun getRanking(completion: (ranking: List<RankingItemModel>) -> Unit) {
        repo.getRanking()
                .subscribe { it -> completion(it) }
                .addTo(disposable)
    }

    fun getPrizes(completion: (prizes: List<PrizeItemModel>) -> Unit) {
        repo.getPrizes()
                .subscribe { it -> completion(it) }
                .addTo(disposable)
    }
}