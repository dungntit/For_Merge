package com.ttvnp.ttj_point_business_user_android_client.presentation.tabs

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.util.Log
import com.ttvnp.ttj_point_business_user_android_client.R
import com.ttvnp.ttj_point_business_user_android_client.databinding.ActivityTabsBinding
import dagger.android.support.DaggerAppCompatActivity
import com.ttvnp.ttj_point_business_user_android_client.model.TabModel
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

enum class TabEnum(val instance: TabModel ) {
    CAMPAIGN(TabModel(R.string.tab_campaign, R.drawable.tabbar_icon_campaign)),
    RANKING(TabModel(R.string.tab_ranking, R.drawable.tabbar_icon_ranking)),
    WALLET(TabModel(R.string.tab_wallet, R.drawable.tabbar_icon_wallet)),
    //NOTICE(TabModel(R.string.tab_notice, R.drawable.tabbar_icon_notice)),
    PROFILE(TabModel(R.string.tab_profile, R.drawable.tabbar_icon_profile))
}

class TabsActivity : DaggerAppCompatActivity() {
    private lateinit var binding: ActivityTabsBinding

    companion object {
        const val ACTIVE_TAB_INTENT_KEY = "ACTIVE_TAB_INTENT_KEY"

        fun intent(context: Context, activeTab: TabEnum = TabEnum.CAMPAIGN): Intent {
            return Intent(context, TabsActivity::class.java).apply {
                putExtra(TabsActivity.ACTIVE_TAB_INTENT_KEY, activeTab)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tabs)
        setViews()
        if (intent.hasExtra("currentTab")) {
            this.binding.pager.currentItem = intent.extras.get("currentTab") as Int
        }

    }

    private fun setViews() {
        val manager = supportFragmentManager
        val adapter = ViewPagerAdapter(manager)
        val viewPager = binding.pager

        viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(viewPager)

        val tabs = binding.tabs

        TabEnum.values().forEach {
            val tab = tabs.getTabAt(it.ordinal)
            tab?.setText(it.instance.labelId)
            tab?.setIcon(it.instance.iconId)

        }

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                //val fragment = adapter.findFragmentByPosition(viewPager, position)
                //fragment.fetch()
            }
        })

        val activeTab = intent?.extras?.get(TabsActivity.ACTIVE_TAB_INTENT_KEY) as? TabEnum
        viewPager.currentItem = activeTab?.ordinal ?: TabEnum.CAMPAIGN.ordinal
    }
}
