package com.ttvnp.ttj_point_business_user_android_client.presentation.campaign

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import com.ttvnp.ttj_point_business_user_android_client.R
import com.ttvnp.ttj_point_business_user_android_client.databinding.ActivityCampaignDetailBinding
import com.ttvnp.ttj_point_business_user_android_client.model.ShopModel
import com.ttvnp.ttj_point_business_user_android_client.presentation.tabs.TabsActivity
import com.ttvnp.ttj_point_business_user_android_client.presentation.tabs.TabEnum
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_campaign_detail.*


class CampaignDetailActivity : DaggerAppCompatActivity() {
    // @Inject lateinit var viewModel: LoginViewModel
    private val binding: ActivityCampaignDetailBinding by lazy {
        DataBindingUtil.setContentView<ActivityCampaignDetailBinding>(
            this, R.layout.activity_campaign_detail
        ) as ActivityCampaignDetailBinding
    }

    companion object {
        val TAG: String = CampaignDetailActivity::class.java.simpleName
        fun intent(context: Context): Intent {
            return Intent(context, CampaignDetailActivity::class.java).apply { }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.setShop(intent.getSerializableExtra("campaign") as ShopModel)

        imagePlaceholder.setOnClickListener { view ->
            Log.d(TAG,"click imagePlaceholder")
        }

        imageWallet.setOnClickListener { view ->
            startActivity(TabsActivity.intent(applicationContext, TabEnum.WALLET))
        }

    }
}
