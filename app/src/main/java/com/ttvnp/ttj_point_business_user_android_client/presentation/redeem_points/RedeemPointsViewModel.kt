package com.ttvnp.ttj_point_business_user_android_client.presentation.redeem_points

import com.ttvnp.ttj_point_business_user_android_client.model.TransactionModel
import com.ttvnp.ttj_point_business_user_android_client.network.repository.TransactionRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class RedeemPointsViewModel @Inject constructor(private val repo: TransactionRepository) {
    val disposable = CompositeDisposable()

    fun usePoint(value: Long, completion: (transaction: TransactionModel) -> Unit) {
        repo.usePoint(value)
                .subscribe { transaction -> completion(transaction) }
                .addTo(disposable)

    }
}