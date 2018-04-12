package com.ttvnp.ttj_point_business_user_android_client.di

import com.ttvnp.ttj_point_business_user_android_client.presentation.campaign.CampaignFragment
import com.ttvnp.ttj_point_business_user_android_client.presentation.profile.ProfileFragment
import com.ttvnp.ttj_point_business_user_android_client.presentation.ranking.RankingFragment
import com.ttvnp.ttj_point_business_user_android_client.presentation.wallet.WalletFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {
    @ContributesAndroidInjector
    fun contributeCampaignFragment(): CampaignFragment

    @ContributesAndroidInjector
    fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    fun contributeRankingFragment(): RankingFragment

    @ContributesAndroidInjector
    fun contribudeWalletFragment(): WalletFragment
}