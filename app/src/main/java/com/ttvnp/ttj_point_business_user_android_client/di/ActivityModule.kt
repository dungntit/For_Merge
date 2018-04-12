package com.ttvnp.ttj_point_business_user_android_client.di

import com.ttvnp.ttj_point_business_user_android_client.presentation.login.LoginActivity
import com.ttvnp.ttj_point_business_user_android_client.presentation.sign_up.SignUpActivity
import com.ttvnp.ttj_point_business_user_android_client.presentation.main.MainActivity
import com.ttvnp.ttj_point_business_user_android_client.presentation.profile_edit.ProfileEditActivity
import com.ttvnp.ttj_point_business_user_android_client.presentation.tabs.TabsActivity
import com.ttvnp.ttj_point_business_user_android_client.presentation.campaign.CampaignDetailActivity
import com.ttvnp.ttj_point_business_user_android_client.presentation.complete.CompleteActivity
import com.ttvnp.ttj_point_business_user_android_client.presentation.activate.ActivateActivity
import com.ttvnp.ttj_point_business_user_android_client.presentation.redeem_points.RedeemPointsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {
    @ContributesAndroidInjector
    fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    fun contributeLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    fun contributeSignUpActivity(): SignUpActivity

    @ContributesAndroidInjector
    fun contributeActivateActivity(): ActivateActivity

    @ContributesAndroidInjector
    fun contributeCompleteActivity(): CompleteActivity

    @ContributesAndroidInjector
    fun contributeProfileEditActivity(): ProfileEditActivity

    @ContributesAndroidInjector
    fun contributeTabsActivity(): TabsActivity

    @ContributesAndroidInjector
    fun contributeCampaignDetailActivityActivity(): CampaignDetailActivity

    @ContributesAndroidInjector
    fun contributeRedeemPointsActivity() :RedeemPointsActivity
}
