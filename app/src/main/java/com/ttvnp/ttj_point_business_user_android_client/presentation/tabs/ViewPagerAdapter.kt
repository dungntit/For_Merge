package com.ttvnp.ttj_point_business_user_android_client.presentation.tabs

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.ttvnp.ttj_point_business_user_android_client.presentation.campaign.CampaignFragment
import com.ttvnp.ttj_point_business_user_android_client.presentation.profile.ProfileFragment
import com.ttvnp.ttj_point_business_user_android_client.presentation.notice.NoticeFragment
import com.ttvnp.ttj_point_business_user_android_client.presentation.ranking.RankingFragment
import com.ttvnp.ttj_point_business_user_android_client.presentation.wallet.WalletFragment
import android.support.v4.view.ViewPager


class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        var fragment: Fragment? = null

        when (position) {
            0 -> fragment = CampaignFragment.newInstance()
            1 -> fragment = RankingFragment.newInstance()
            2 -> fragment = WalletFragment.newInstance()
            //3 -> fragment = NoticeFragment.newInstance()
            3 -> fragment = ProfileFragment.newInstance()
            else -> {
            }
        }
        return fragment
    }

    override fun getCount(): Int {
        return 4
    }
}